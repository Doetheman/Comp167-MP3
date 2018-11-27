* Gamer Profile Data File Format
Add the total points to the end of the gamer data.  Here is the new format for one gamer
gamer0 firstname , gamer0 lastname, gamer0 gamerId
gamer0Rank:int  gamer0GamesPlayed:int  gamer0TorpsFired:int  gamer0MeteorsHit  gamer0HighScore gamer0TotalScore

* CraftData
The phaserSoundFile property was misspelled
-phaserSoundFile : String

* MeteorIO
Change the parameter from a filename to the GamerProfileCollection object.
+writeGamerProfileData( GamerProfileCollection gpCollection ) : void

* GamerProfile
GamerID should be of type String

* Driver Info
MeteorBlasterDriver project, MeteorBlasterGUI2.jar, and Integration Instructions added
