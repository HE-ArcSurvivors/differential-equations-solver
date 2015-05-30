
package tools;

import javax.swing.ImageIcon;


/**
* Les images doivent se trouver dans un jar, et le jar dans le classpth!
* Le jar doit contenir le folder ressources. A l'interieur du folder ressource doit se trouver les images aux formats (jpg, voir mieux png pour la transparance)
*/
public class MagasinImage
	{

	/*------------------------------------------------------------------*\
	|*		 Version Synchrone (bloquant)								*|
	\*------------------------------------------------------------------*/

	public static final ImageIcon tap = ImageLoader.loadSynchroneJar("ressources/Robinet.png");
	public static final ImageIcon iconEdit = ImageLoader.loadSynchroneJar("ressources/iconEdit-20x20.png");
	public static final ImageIcon iconValidate = ImageLoader.loadSynchroneJar("ressources/iconValidate-20x20.png");
	public static final ImageIcon iconPause = ImageLoader.loadSynchroneJar("ressources/iconPause-20x20.png");
	public static final ImageIcon iconPlay = ImageLoader.loadSynchroneJar("ressources/iconPlay-20x20.png");
	public static final ImageIcon iconReload = ImageLoader.loadSynchroneJar("ressources/iconReload-20x20.png");
	public static final ImageIcon iconSettings = ImageLoader.loadSynchroneJar("ressources/iconSettings-20x20.png");
	public static final ImageIcon iconAdd = ImageLoader.loadSynchroneJar("ressources/iconAdd-20x20.png");
	public static final ImageIcon iconDelete = ImageLoader.loadSynchroneJar("ressources/iconDelete-20x20.png");
	public static final ImageIcon iconStop = ImageLoader.loadSynchroneJar("ressources/iconStop-20x20.png");

	/*------------------------------------------------------------------*\
	|*		Version Assynchrone	(non bloquant)							*|
	\*------------------------------------------------------------------*/

	//public static final ImageIcon warning = ImageLoader.loadAsynchroneJar("./ressources/warning.png");
	//public static final ImageIcon coffee = ImageLoader.loadAsynchroneJar("./ressources/coffee_logo.png");
	//public static final ImageIcon cervin = ImageLoader.loadAsynchroneJar("ressources/cervin.jpg");

	}

