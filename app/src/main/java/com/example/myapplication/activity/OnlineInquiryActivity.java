package com.example.myapplication.activity;

import static com.example.myapplication.tool.JsonTools.loadClinicsFromJson;
import static com.example.myapplication.tool.JsonTools.loadSymptomsFromJson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ChatMessageAdapter;
import com.example.myapplication.entity.ChatMessage;
import com.example.myapplication.entity.Clinic;
import com.example.myapplication.entity.Disease;
import com.example.myapplication.tool.DiseaseMatch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnlineInquiryActivity extends AppCompatActivity implements OnSpecialMessageClickListener {
    private ListView chatListView;
    private EditText messageEditText;
    private Button sendButton;
    private Button exitButton;
    private ChatMessageAdapter chatAdapter;
    private ArrayList<ChatMessage> chatMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_inquiry);

        initViews();
        setupListeners();
    }

    private void initViews() {
//        Objects.requireNonNull(getSupportActionBar()).hide();

        chatListView = findViewById(R.id.chat_list_view);
        messageEditText = findViewById(R.id.et_message_input);
        sendButton = findViewById(R.id.btn_send);
        exitButton = findViewById(R.id.btn_exit);

        chatMessages = new ArrayList<>();
        chatAdapter = new ChatMessageAdapter(this, chatMessages,this);
        chatListView.setAdapter(chatAdapter);
    }

    private void setupListeners() {
        exitButton.setOnClickListener(v -> finish());

        sendButton.setOnClickListener(view -> {
            String message = messageEditText.getText().toString().trim();
            if (!message.isEmpty()) {
                processUserMessage(message);
                messageEditText.setText("");
            } else {
                Toast.makeText(this, "Message cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void processUserMessage(String message) {
        chatMessages.add(new ChatMessage(message, false));
        chatAdapter.notifyDataSetChanged();
        chatListView.setSelection(chatAdapter.getCount() - 1);

        String botResponse = getBotReply(message);
        chatMessages.add(new ChatMessage(botResponse, true));
        chatAdapter.notifyDataSetChanged();
        chatListView.setSelection(chatAdapter.getCount() - 1);


    }

    private String getBotReply(String userMessage) {
        if (isGreeting(userMessage)) {
            return handleGreeting(userMessage);
        }

        Set<String> userSymptoms = extractSymptoms(this, userMessage);
        if (!userSymptoms.isEmpty()) {
            return handleSymptoms(userSymptoms);
        }

        return "I'm not sure I understand. Can you tell me more about your symptoms?";
    }

    private boolean isGreeting(String message) {
        String[] greetings = {"hello", "hi", "hey", "how are you", "good morning", "good afternoon", "good evening", "greetings", "what's up", "how's it going"};

        String regex = "\\b(" + String.join("|", greetings) + ")\\b";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(message);

        return matcher.find();
    }

    private String handleGreeting(String message) {
        String lowerCaseMessage = message.toLowerCase();

        if (lowerCaseMessage.matches(".*\\b(how are you|how's it going|what's up)\\b.*")) {
            return "I'm here to help you. What symptoms are you experiencing?";
        }

        else if (lowerCaseMessage.matches(".*\\b(hello|hi|hey|greetings)\\b.*")) {
            return "Hello! How can I assist you with your health concerns today?";
        }

        return "Welcome! If you have any health-related questions, feel free to ask.";
    }


    private String handleSymptoms(Set<String> userSymptoms) {
        DiseaseMatch bestMatch = findBestMatch(this, userSymptoms);
        if (bestMatch != null) {
            String response = "Based on the symptoms you've described, it might be " + bestMatch.getDisease().getName() + ".";
            response += "  Common symptoms include " + String.join(", ", bestMatch.getDisease().getSymptoms()) + ".";

            if (bestMatch.getClinic() != null) {
                response += "  I recommend that you visit a " + bestMatch.getClinic().getClinic() + " clinic for a more thorough evaluation.\n";
            }

            response += " Remember, the most accurate diagnosis should be made by a doctor. Please take care of yourself and seek medical attention promptly.";

//            // 显示挂号对话框
//            showRegisterDialog();
            // 创建机器人的特殊回复
            ChatMessage botMessage = new ChatMessage("机器人特殊回复内容", true);
            botMessage.setSpecialMessage(true);
            chatMessages.add(botMessage);
            chatAdapter.notifyDataSetChanged();

            return response;
        } else {
            String advice = "I couldn't find a match for your symptoms.";
            advice += " If you're feeling unwell, it's always a good idea to consult a doctor.";
            advice += " Meanwhile, can you provide more details about your symptoms?";
            return advice;
        }

    }

//    private void showRegisterDialog() {
//        new AlertDialog.Builder(this)
//                .setTitle("Make an Appointment")
//                .setMessage("Would you like to go to the registration page to make an appointment?")
//                .setPositiveButton("Yes", (dialog, which) -> {
//                    // 用户选择“是”时的操作，比如跳转到挂号界面
//                    goToRegistrationPage();
//                })
//                .setNegativeButton("No", null) // 用户选择“否”时什么也不做
//                .show();
//    }
//    private void goToRegistrationPage() {
//        Intent intent = new Intent(this, AppointmentActivity.class);
//        startActivity(intent);
//    }
    public Set<String> extractSymptoms(Context context, String userInput) {
        Set<String> extractedSymptoms = new HashSet<>();
        Set<String> knownSymptoms = loadSymptomsFromJson(context);
        for (String symptom : knownSymptoms) {
            Pattern pattern = Pattern.compile("\\b" + symptom + "\\b", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(userInput);
            if (matcher.find()) {
                extractedSymptoms.add(symptom);
            }
        }
        return extractedSymptoms;
    }

    public DiseaseMatch findBestMatch(Context context, Set<String> userSymptoms) {
        List<Clinic> clinics = loadClinicsFromJson(context);
        Disease bestMatch = null;
        Clinic bestMatchClinic = null;
        int maxMatchCount = 0;

        for (Clinic clinic : clinics) {
            for (Disease disease : clinic.getDiseases()) {
                int matchCount = disease.matchSymptoms(userSymptoms);
                if (matchCount > maxMatchCount) {
                    bestMatch = disease;
                    bestMatchClinic = clinic;
                    maxMatchCount = matchCount;
                }
            }
        }

        return new DiseaseMatch(bestMatch, bestMatchClinic);
    }


    @Override
    public void onSpecialMessageClick() {
        goToRegistrationPage();
    }
    private void goToRegistrationPage() {
        Intent intent = new Intent(this, AppointmentActivity.class);
        startActivity(intent);
    }
}
