

def width = 30
def height = 120
def thickness = 7

CSG bookmark = new RoundedCube(width,height,thickness)
				.cornerRadius(5.2)
				.toCSG()