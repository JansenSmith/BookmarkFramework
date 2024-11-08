import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cube
import eu.mihosoft.vrl.v3d.Cylinder
import eu.mihosoft.vrl.v3d.RoundedCube
import eu.mihosoft.vrl.v3d.Transform


// Load an STL file from a git repo
// Loading a local file also works here
File stlFile = ScriptingEngine.fileFromGit(
	"https://github.com/JansenSmith/BookmarkFramework.git",
	"source_stl/pirates3_Front_68x205_S_C_meshlab.stl");
// Load the .CSG from the disk and cache it in memory
CSG source  = Vitamins.get(stlFile);
CSG placeholder = new Cube(68.4,205,3).toCSG()
//source = placeholder
source = source.toZMin()

def width = source.getTotalX()
def height = source.getTotalY()
def thickness = source.getTotalZ()

def eyehole_diameter = 5.6
def eyehole_fromtop = 5.25

def bookmark = new RoundedCube(width,height,100)
				.resolution(32)
				.cornerRadius(5.2)
				.toCSG()

bookmark = bookmark.difference(new Cube(width,height,100).toCSG().movez(-50))
					.difference(new Cube(width, height, 100).toCSG().toZMin().movez(thickness))

def eyehole = new Cylinder(eyehole_diameter/2,thickness,(int)32).toCSG()
					.toZMin()
					.toYMax()
					.movey(bookmark.getMaxY())
					.movey(-eyehole_fromtop)
bookmark = bookmark.difference(eyehole)
bookmark = bookmark.intersect(source)

bookmark = bookmark.setColor(javafx.scene.paint.Color.PINK)
			.setName("bookmark_pirate3")
			.addAssemblyStep(0, new Transform())
			.setManufacturing({ toMfg ->
				return toMfg
						//.rotx(180)// fix the orientation
						//.toZMin()//move it down to the flat surface
			})
					
return bookmark

