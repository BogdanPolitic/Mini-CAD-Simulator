# Mini-CAD-Simulator
A CAD (Computer Aided Design) Java Simulator

	This is a Java project.
	The input file location must be given as the only argument, when starting the executable.
	The output file location is "./drawning.png".

	The input file format should be:

		-------------------------------------------
		<number of elements (including the canvas)>
		<element-name (with uppercase)> <positioning> #<color-code> <opacity-percentage>
		.
		.
		.
		-------------------------------------------

	The <positioning> field differs on each element type.
	The first element-name specified should be the CANVAS.
	The following elements should be of one of the types: LINE, SQUARE, CIRCLE, RECTANGLE.
	Below, all types of elements are listed:

		----------------------------------------------------------------------------------------------------------------------------------------------------------
		CANVAS <image-height> <image-width> #<color-code> <opacity-percentage>
		LINE <pointA-x-coordinate> <pointA-y-coordinate> <pointB-x-coordinate> <pointB-y-coordinate> #<color-code> <opacity-percentage>
		SQUARE <top-left-corner-x-coordinate> <top-left-corner-y-coordinate> <edge-length> #<color-code> <opacity-percentage>
		RECTANGLE <top-left-corner-x-coordinate> <top-left-corner-y-coordinate> <horizontal-edge-length> <vertical-edge-length> #<color-code> <opacity-percentage>
		CIRCLE <center-point-x-coordinate> <center-point-y-coordinate> <radius> #<color-code> <opacity-percentage>
		----------------------------------------------------------------------------------------------------------------------------------------------------------

	An example of an input file is "./input.txt".
