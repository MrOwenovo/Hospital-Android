Please read the following words if it's your first time here~
===
### Preview
<img src="https://img.gejiba.com/images/7aa6e0f944a3ee055cad5006ac02c692.png" alt="微信图片 20231214164500" border="0">
<img src="https://img.gejiba.com/images/b158ca68460f01a68db5bf2476b73dff.png" alt="微信图片 20231214164527" border="0">
<img src="https://img.gejiba.com/images/ca5df1a57e4e1b5880c4e55622bdb1fc.png" alt="微信图片 20231214164534" border="0">
<img src="https://img.gejiba.com/images/fa5e574af570724fceb170f61e5f66d0.png" alt="微信图片 20231214164610" border="0">

This repository is created for the group project of CAN301 (from Xi'an Jiaotong-Liverpool University) during the first semester of 2023-24 academic year. The name of our group is High Score Please!<br>
Anyone in our group is free to update the files unmaliciously.<br>

Pleaseeeeeeeeeeeeeeee first PULL the whole repo using `'git pull origin master'` and then modify your code locally in case of unsynchronization.
---

The basic operations are listed below (The first 14 items are from Soon Phei Tin):
---
Local vs. Remote Repository<br>
Creating, managing, and synchronizing repository<br>
1.Create a new local repository using `git init`<br>
2.Check the status of the files in the repository using `git status`<br>
3.Track the files using `git add`<br>
&emsp; a.Use `git add .` to track all untracked files<br>
4.Commit the tracked files to the repository using `git commit –m “commit message”`<br>
5.Exercise: <br>
&emsp; a.Add 3.txt to the repository. Check the status<br>
&emsp; b.Modify 2.txt. Check the status<br>
&emsp; c.Commit 2.tx and 3.txt to the repository.<br>
6.Get your GitLab account if you don’t have one. Create one Remote Repository<br>
7.Add a remote repository to your local project using `git remote add origin “<repository URL>”`<br>
8.Push the local repository to the remote repository using `git push origin master`<br>
9.Programmer 2 Cloning Repository: Refer to the earlier document on LM<br>
10.Programmer 1 add d.txt and edit the file. Commit the change, and push (Step 8) the change to the remote repository.<br>
11.Programmer 2 synchronize the code using `git pull origin master`<br>
12.Check the details of your every commit using `git log`<br>
13.At any point of time when you need to revert your code, use `git revert <commit-id>`<br>
&emsp; a.The revert is done only for local repository<br>
&emsp; b.You need to do git push in order for the remote to take effect.<br>
&emsp; c.Programmer 2 will not see the change unless (s)he did `git pull`<br>
14.You can compare the content of your commits using `git diff <commit ID 1> <commit ID 2>`<br>
15.You can create a new branch using `git branch branch_name`<br>
16.You can switch to a branch using `git checkout jpa`<br>
17.You can merge your branch to the main branch using `git merge jpa`, this line of code should be written when you are in the main branch.<br>
