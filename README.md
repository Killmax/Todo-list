#TO DO LIST

This app is a simple Android application (for Android 4.4 and below) to manage task to do with a title and a content

## How to use the application

On your first use, this is what you will see :

![](http://i.imgur.com/v4Olbx3.png)

Then click on the button ``New Task``.

A dialog box will appear :

![](http://i.imgur.com/GB8o278.png)

Fill the field ``Title`` and ``Content``, then click on ``Add``. Your task is now saved in the application.

If you want to edit a task, or mark it as done, just long click on the task.

Another dialog box will appear :

![](http://i.imgur.com/8UK05Gc.png)

And here you can modify or delete your task.

## Development

There's two activities in the project ;

*   ``MainActivity``  where everything is orchestrated
	* ``onCreate`` initialized the application and the list of task
	* ``setupListViewListener`` setup the listener for the different task on the list (click and long click) and  the edit dialog box showed before
	* ``onAddItem`` is the function called when the button ``New Task`` is pressed
	* ``readItems`` and ``writeItems`` are the low-level function used for the persistency of the data
*  ``SplashScreenActivity`` for the splash screen

## Install

To build the project, run :

```bash
gradle build
```

To create the APK :

```bash
gradle assembleRelease
```