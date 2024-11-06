

// Load an STL file from a git repo
// Loading a local file also works here
File stlFile = ScriptingEngine.fileFromGit(
	"https://github.com/JansenSmith/BookmarkFramework.git",
	"source_stl/pirates1_Front_68x205_S_C.stl");
// Load the .CSG from the disk and cache it in memory
CSG source  = Vitamins.get(stlFile);

def width = 30
def height = 120
def thickness = 7

CSG bookmark = new RoundedCube(width,height,thickness)
				.cornerRadius(5.2)
				.toCSG()