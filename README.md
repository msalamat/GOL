# Game of Life

### How to run
Put all the files in a single directory, like in here:

`$momo/Desktop/GOL`

then

`cd ~/Desktop/GOL`

and compile:
		`javac *.java`
then run:
		`java Main`

Don't forget to click to progress to the next state!

> Note: Compiles fine with Java 8 and 9. Untested for other versions of Java.

### Notes
* Two buttons are there. You can save the current state and also load one. 
  You can also save multiple versions, and they will have unique filenames.

* With the upload button (the small one with the SVG icon), it will default 
  open to the /loads folder. You can't select any other extension other than
  .ser so in a way it is safe.

* Saved data is made in a folder called loads. If you don't have one,
  the program will make one for you.

* I put the bulk of the action in a method called `act()`. This method
  now does two things. For non-plants, it first sees if it can have some sex
  and possibly give birth. Then, it looks around for food to eat. In the case
  of plants, it does not look for any food to eat, but it does in fact try
  to have sex.
  	* The alternative to this is to make some method 'haveSex' and another one like
  'move', but I felt like the code is clear enough and anyone reading it can
  understand what is going on. So to just make these methods for the sake of
  making methods isn't what I think would be a good idea. 

* If you click anywhere on the scene (not the button), it goes to the next state. However,
  if you click on the button, but then release on the scene (not hovering over same button), 
  it will actually treat it as a press on the scene. If I had more energy I'd fix it, but it is
  not required for this assignment and I think I already did enough.

Lastly, on the press of the upload button, if you are running with MacOS (not sure about others), you get this notification in the terminal:

objc[3068]: Class FIFinderSyncExtensionHost is implemented in both /System/Library/PrivateFrameworks/FinderKit.framework/Versions/A/FinderKit (0x7fffb4689c90) and /System/Library/PrivateFrameworks/FileProvider.framework/OverrideBundles/FinderSyncCollaborationFileProviderOverride.bundle/Contents/MacOS/FinderSyncCollaborationFileProviderOverride (0x13c4d5cd8). One of the two will be used. Which one is undefined.

However, I found the same notification experienced by others online and a SO post said that it is harmless, since
for Macs, they do things a bit differently. Perhaps it doesn't appear on Windows.

### TODO
* Change the pseudo pseudo random number generator to something actually random so the simulation is different every time.
