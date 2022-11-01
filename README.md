# Graph Map Analysis - Grama

## <u>Présentation du projet</u>

Ce projet a pour vocation d'explorer et d'analyser du `graphe-MAP` via une ` interface homme-machine`, entre autre une **carte** contenant des `nœuds (localité ou site)` et des `liens (routes)` depuis une interface graphique manipulable par un utilisateur.

L'initiative de la mise en place du projet est due à une étude sur les graphes mathématiques au sein de l'IUT, cette dernière requière un ensemble de matières afin de réaliser le projet.

<u>Liste des matières concernées</u> :

* Programmation IHM `(interface homme machine)`
* Graphes / Mathématiques
* Structure de données
* Java
* Qualité développement
* Gestion de projet 

## Notion Fondamentales

En premier lieu nous devons expliciter les notions fondamentales afin de comprendre les différents concepts du projets

### Notion mathématiques :

> #### Les Graphes
>
> En mathématiques, et plus précisément en **théorie des graphes**, un graphe est une structure composée d'objets dans laquelle certaines paires d'objets sont en relation. Les objets correspondent à des abstractions mathématiques et sont appelés sommets `(ou nœuds ou points)`, et les relations entre sommets sont des arêtes `(ou liens ou lignes)`. On distingue les graphes non orientés, où les arêtes relient deux sommets de manière symétrique, et les graphes orientés, où les arêtes, alors appelées flèches `(ou arcs)`, relient deux sommets de manière asymétrique.

<u>Exemple de graphes non orienté</u> : 



<img src="https://cdn.discordapp.com/attachments/897855194212212746/948520965917261884/unknown.png" alt="img" style="zoom:50%;" />



> #### Les Graphes pondérés
>
> Un graphe pondéré avec dix sommets et douze arêtes. Un graphe pondéré est un graphe où chaque arête porte un nombre `(son poids)`. 
> Ces poids peuvent représenter par exemple des coûts, des longueurs ou des capacités, en fonction du problème traité. 
> Ces graphes sont fréquents dans divers contextes, comme le problème de plus court chemin ou le problème du voyageur de commerce.

<u>Exemple de graphe pondéré</u> :



![img](https://cdn.discordapp.com/attachments/897855194212212746/948526106284261406/unknown.png)



> #### Les Graphes connexes
>
> Un graphe connexe est un graphe non orienté où toute paire de sommets est reliée par une chaîne. Un graphe orienté, est connexe si le graphe non orienté obtenu en oubliant les orientations des arêtes est connexe. Il est fortement connexe si tout couple de sommets est relié par un chemin, donc si pour tout couple (x, y) de sommets, il y a un chemin de x à y et un chemin de y à x. Un graphe k-sommet-connexe est un graphe qui possède au moins k+1 sommets et qui reste encore connexe après en avoir ôté k–1 ; de même, un graphe k-arête-connexe est un graphe qui reste connexe quand on lui enlève k–1 arêtes. 



<img src="https://cdn.discordapp.com/attachments/897855194212212746/948532394854604820/unknown.png" alt="img" style="zoom:80%;" />





### Notion informatique :

> #### Les structures de données
>
> Les structures de données statiques ont des tailles, des structures et des emplacements de mémoire fixes au moment de la compilation. Les structures de données dynamiques ont des tailles, des structures et des emplacements de mémoire qui peuvent diminuer ou augmenter selon l'utilisation.

## Phases de développement

### 1. Construction du Graph-MAP

Dans le cas de notre projet, nous étudierons exclusivement les graphes non orientés et pondérés en arêtes (kilométrages), avec 3 types de nœuds (V,R et L) et 3 types d’arêtes (A,N et D).

Il devra être connexe et contenir au moins 30 nœuds avec :

• Nœuds : 3/5 de villes, 1/5 de restaurants et 1/5 de centres de loisirs

• Liens : 1.5/5 d’autoroutes, 1.5/5 de nationales et 2/5 de départementales 

• Possibilités d’avoir plusieurs liens (A, N et/ou D) entre deux mêmes villes



**<u>Exemple de Graphe-MAP</u>** :



![img](https://cdn.discordapp.com/attachments/897855194212212746/948519054031847454/Capture_decran_2022-03-02_105452.png)



**Objectif/Sortie** : Exploration et analyser du graphe-MAP via Interface IHM 

**NŒUDS** (localité ou site) : V: VILLE, L: LOISIR et R: RESTAURANT 

**LIENS** (routes) A: Autoroute, N: Nationale, D: Départementale



### 2. Chargement du Graph-Map

Le Graphe-MAP doit être donné en fichier CSV ou texte où chaque ligne est décrite ainsi : 

`Nœud:lien1::voisin1; lien2::voisin2;lien3::voisin3;.........;lienk::voisink;; `

> **Exemple** :
>
> V, Macon: A,30::R,Les Echets;N,50::V, Villeurbanne;A,60::V,Meyzieu;;
>
> V,Meyzieu:A,60::V,Macon;D,5::McDo-Decines;; R,McDo-Decines:D,5::V,Meyzieu.... ..... 

Le Graphe-MAP doit être chargé en mémoire dans une structure de données dynamique (listes chainées). 

### 3. Interrogation du Graph-Map via une interface IHM

#### Analyser les éléments du Graph `(0-distance)`

Donner la possibilité d’afficher ces éléments à la demande sur l’interface IHM : 

* Villes, centres de loisirs et restaurants (NŒUDS), 
* Autoroutes, nationales et départementales (LIENS) 
* lister par catégories NŒUDS et LIENS. 
* Nombres : villes, de restaurants, de centres de loisir, d’autoroutes, de nationales, de départementales dans le Graphe - MAP

#### Comprendre les contextes des éléments du graphe `(1-distance) `

* Pour un nœud donné, possibilité de connaitre les voisins à 1-saut (voisin direct) : 

  exemples : quels sont les restaurants voisins à Décines (voisins directs) ?, quels sont les centres de loisirs voisins aux Echets (voisins directs) ? 

* Pour un lien/arête donné, possibilité de connaitre les villes qu’il relie



<img src="https://cdn.discordapp.com/attachments/897855194212212746/950661461359886366/unknown.png" alt="img" style="zoom:80%;" />

#### Connaitre les distances entre deux sites `(2-distance)`

On choisit 2 sites quelconques du graphe sur l’IHM, l’application doit pouvoir dire s’ils sont à 2-distance ou pas. 

<img src="https://cdn.discordapp.com/attachments/897855194212212746/950662089381400626/unknown.png" alt="img" style="zoom:80%;" />

#### Comparer deux villes `(>= 2-distance)`

Pour 2 villes quelconques A et B sur l’IHM, l’application doit pouvoir dire si : 

* A est plus/moins OUVERTE que B : 

  Si le nombre de villes à 2-distance de A est sup/inf au nombre de villes à 2-distance de B 

* A est plus/moins GASTRONOMIQUE que B : 

  Si le nombre de restaurants à 2-distance de A est sup/inf au nombre de restaurants à 2-distance de B 

* A est plus/moins CULTURELLE que B : 

  Si le nombre de centres de loisir à 2-distance de A est sup/inf au nombre de restaurants à 2-distance de B

#### Connaitre les distances entre deux sites `(p-distance)`

Etant donnés 2 sites quelconques du graphe via l’IHM, l’application doit être en mesure de : 

* Calculer la distance la plus courte entre eux 
* Trouver une route traversant, une ville, un lieu de loisir, un restaurant 
* Voire plus, passant par 2 villes, un restaurant et un centre de loisir, 2 centres de loisir,...

### 3. Résultats et sorties IHM

* **Ecran principal** (chargement du Graphe-MAP : informer si le graphe est bien chargé et mémoire ou pas, visualiser le graphe,...) 
* **Ecran 0** : (affichage -> 0-distance) 
* **Ecran 1** : voisinage direct -> 1-distance
* **Ecran 2** : voisinage à 2 sauts -> 2 distance 
* **Ecran 3** : comparaison de sites à 2 sauts ou plus --> >= 2 distance
* **Ecran 4** `(bonus)` : voisinage à p sauts et chemins -> p distance
