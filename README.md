# MyBookMark
BookMark app for desktop web browser

I have been using computers for a long time, since around the year 2000. So I had several computers and I had to fix my computers several times and for several times I had to reinstall the operating system and end up losing the bookmark from the web browser I was using. Making backups from the browser wouldn't work so well when I had to reinstate the backup and I almost never had a backup from the browser's bookmark prior to the computer breaking.

So I decided I would make my own bookmark app using a proper database - MySQL in this case. This way I would have my personal bookmark as a Java webapp which is way better than the bookmark from the web browser. I make periodic backups from my MySQL database and store in different places.

The Key points here are two:

1 - Not losing my bookmark ever again. I may lose some links I stored if prior to my computer breaking I haven't made a backup but most of my links would be stored in my database that I keep backups.

2 - Having a better and custom experience in comparison to the book mark of a web browser, like the Firefox, for example.

This way I simple bookmark the web app from my Tomcat server in my browser to be opened when I open the web browser. So the app comes automatically when I open my browser and I have all the links I use the most as a web page which is much better and faster than the bookmark from the browser.

-------------------------------------
Installation
-------------------------------------

Inside the resources folder which is placed inside the webapp folder, you gonna find how to install the Tomcat server as a service in a Linux distribution, like Linux Mint, for example.

You gonna have look for yourself how to configure the MySQL database and the app. This is aimed at programmers who know how to make all that.

-------------------------------------
Technologies used
-------------------------------------

Java, Hibernate, HTML, CSS, AngularJS, MomentJS, Just a bit of JQuery and MySQL.

-------------------------------------
What the app does? - Requirements
-------------------------------------

Store Links, Chronometers, Countdowns, Monthly and Annual Reminders with plently of space to store.

-------------------------------------
How to Use? - Use Cases
-------------------------------------

After all installed, all you gonna have to do is to use the app to store Links, Chronometers, Countdowns, Monthly and Annual Reminders.

The only thing is that you gonna make some manual work like copy and paste to create the links which is harder than to use the bookmark from the browser but after the link is persisted in the database you won't lose your bookmark anymore as long as you keep backups from the database.

-------------------------------------
Backups
-------------------------------------

This is the command I use to make my backups:

sudo mysqldump -u root -p --databases mybookmark > MySQLBackup_MyBookMark/Backup-"`date +"%d-%m-%Y_%H-%M"`".sql

You may customize as you like

-------------------------------------

Enjoy
