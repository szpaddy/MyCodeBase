 accessing your application's data folder without root access
 ==========================================
 [摘自](http://stackoverflow.com/questions/3259380/cant-access-data-folder-in-the-file-explorer-of-ddms-using-a-nexus-one)
 
 Turns out there is a simple solution, the run-as command.

run-as com.your.package ls -l /data/data/com.your.package
run-as com.your.package rm /data/data/com.your.package/databases/mydatabase.db

That will allow you to run commands as your app. You can also use run-as in interactive mode.

run-as com.your.package
shell@android:/data/data/com.your.package $ ls
cache
databases
lib
shared_prefs
rm databases/mydatabase.db

Interactive mode will drop you into the data folder for your app. You can navigate from there.



adb shell
run-as com.paddy.mynotes
cat databases/notes.db > /mnt/sdcard/notes.db3

adb pull /mnt/sdcard/notes.db3 c:\

