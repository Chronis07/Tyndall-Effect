# Tyndall Effect

The basic objective of this project is to use the techniques taught while making a 
ray tracer to simulate the “Tyndall Effect” that occurs from the scattering of light 
due to particles in its medium. A scene with a foggy/dusty room with a window for 
light source will be generated to display the Tyndall effect.

## Approach

The approach taken to accomplish this is to implement Ray marching over the ray 
tracing method taught in this semester. The ray tracer is used to recreate the silhouette 
of the window on the floor. Then Ray marching is used to generate the God rays from 
a point light source through the window.

The basic method involved in Ray marching is to cast rays from the light source to the 
floor and to track points on the ray at equal intervals. Each point on the ray holds a 
certain energy factor which becomes less when it is further away from the light 
source. Then we check if those points are visible in the view frame and if it is, then we 
map the point coordinates to the coordinates of the pixel, and scale its color with the 
energy factor of the ray point.

This step is repeated for multiple rays being casted through the window to the scene. 
This method is further developed by using a random function to generate points at 
random locations on the ray, instead of a uniform distribution. The aim is to give the 
medium of the light rays a more foggy / dusty feel. 

## Getting Started

### Dependencies

* Describe any prerequisites, libraries, OS version, etc., needed before installing program.
* ex. Windows 10

### Installing

* How/where to download your program
* Any modifications needed to be made to files/folders

### Executing program

* How to run the program
* Step-by-step bullets
```
code blocks for commands
```

## Help

Any advise for common problems or issues.
```
command to run if program contains helper info
```

## Authors

Contributors names and contact info

ex. Dominique Pizzie  
ex. [@DomPizzie](https://twitter.com/dompizzie)

## Version History

* 0.2
    * Various bug fixes and optimizations
    * See [commit change]() or See [release history]()
* 0.1
    * Initial Release

## License

This project is licensed under the [NAME HERE] License - see the LICENSE.md file for details

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [PurpleBooth](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2)
* [dbader](https://github.com/dbader/readme-template)
* [zenorocha](https://gist.github.com/zenorocha/4526327)
* [fvcproductions](https://gist.github.com/fvcproductions/1bfc2d4aecb01a834b46)
