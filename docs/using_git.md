# git basics:

Before making changes to the codebase, create a local branch named after you and the feature/change you're making
_Note that you are creating this branch before you start changing files_
```
$ cd $WORKSPACE/ics372
$ git checkout master && git pull # make sure you have the latest changes to avoid merge conflicts
$ git checkout -b yourname/describe-your-awesome-change
# (The -b creates the branch and while you are checking it out.)
# Make your changes
$ git add FILE_WORKED_ON
$ git commit -m 'NAME: Made an awesome change to something'
$ git push -u origin yourname/describe-your-awesome-change # Push changes to origin
```
You should see an output similar to this:
```
Enumerating objects: 4, done.
Counting objects: 100% (4/4), done.
Delta compression using up to 8 threads
Compressing objects: 100% (3/3), done.
Writing objects: 100% (3/3), 657 bytes | 657.00 KiB/s, done.
Total 3 (delta 1), reused 0 (delta 0)
remote: Resolving deltas: 100% (1/1), completed with 1 local object.
remote: 
remote: Create a pull request for 'yourname/describe-your-awesome-change' on GitHub by visiting:
remote:      https://github.com/amymatic/ics372/pull/new/yourname/describe-your-awesome-change
remote: 
To https://github.com/amymatic/ics372.git
 * [new branch]      yourname/describe-your-awesome-change -> yourname/describe-your-awesome-change
Branch 'yourname/describe-your-awesome-change' set up to track remote branch 'yourname/describe-your-awesome-change' from 'origin'.
```

Notice the github URL that's generated - if you paste that into a browser, a pull request will be created for your branch.

To check what files git has noticed changes to, run
```
git status
```

To check what branches you have locally, run
```
git branch
```

To switch to a different branch, run
```
git checkout name-of-branch
```