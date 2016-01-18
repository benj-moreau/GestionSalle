

object Systeme {
  var batiments : Map[Int,Batiment] = Map()
  var materielsMobile : Map[Int,Materiel] = Map()
  var typesMateriel : Map[String,TypeMateriel] = Map()
  var typesSalle : Map[String,TypeSalle] = Map()
  var demandeurs : Map[Int,Demandeur] = Map()
  var titres : Map[String,Titre] = Map()
  var origines : Map[String,Origine] = Map()
  var durees : Map[String,Duree] = Map()
  var manifestations : Map[String,Manifestation] = Map()
  var reservations : Map[Int,Reservation] = Map()
  
  def ajouterBatiment( batiment:Batiment ) = { //modifie le batiment si deja existant
   batiments += (batiment.no_bat -> batiment)
  }
  
  def supprimerBatiment( no_bat:Int ) = {
    batiments -= (no_bat)
  }
  
  def ajouterSalle( no_bat:Int, salle:Salle ) = {
    batiments(no_bat).ajouterSalle(salle)
  }
  
  def supprimerSalle( no_bat:Int, no_salle:Int ) = {
    batiments(no_bat).supprimerSalle(no_salle)
  }
  
  def ajouterMateriel( materiel:MaterielMobile ) = {
    materielsMobile += (materiel.code_inv -> materiel)
  }
  
  def supprimerMateriel( code_inv:Int ) = {
    reservations.foreach(kv => kv._2.materiels -= (code_inv))
    materielsMobile -= (code_inv)
  }
  
  def ajouterMaterielFixe( materiel:MaterielFixe, no_bat:Int, no_salle:Int ) = {
    batiments(no_bat:Int).ajouterMateriel(materiel, no_salle)
  }
  
  def supprimerMaterielFixe( code_inv:Int, no_bat:Int, no_salle:Int ) = {
    batiments(no_bat:Int).supprimerMateriel(code_inv, no_salle)
  }
  
  def ajouterTypeMateriel( typemat:TypeMateriel ) = {
    typesMateriel += (typemat.nom -> typemat)
  }
  
  def ajouterTypeSalle( typesal:TypeSalle ) = {
    typesSalle += (typesal.nom -> typesal)
  }
  
  def ajouterDemandeur( demandeur:Demandeur ) = {
   demandeurs += (demandeur.no_dem -> demandeur)
  }
  
  def supprimerDemandeur( no_dem:Int ) = {// on supprime aussi les reservations du demandeur
    reservations.foreach(kv => if(kv._2.demandeur.no_dem==no_dem){reservations -= (kv._1) })
    demandeurs -= (no_dem)
  }
  
  def ajouterTitre( titre:Titre ) = {
    titres += (titre.nom -> titre)
  }
  
  def ajouterOrigine( orig:Origine ) = {
    origines += (orig.nom -> orig)
  }
  
  def ajouterDuree( duree:Duree ) = {
    durees += (duree.nom -> duree)
  }
  
  def ajouterManifestation( manif:Manifestation ) = {
    manifestations += (manif.nom -> manif)
  }
  
  def ajouterReservation( resa:Reservation ) : Boolean = {
   reservations.foreach(kv => if((kv._2.no_bat.equals(resa.no_bat)) && (kv._2.no_salle.equals(resa.no_salle)) && (resa.duree.concomitant(kv._2.duree))){return false})
   reservations += (resa.ref_resa -> resa)
   calculerTarifReservation( resa.ref_resa )
   return true
  }
  
  
  def supprimerReservation( ref_resa:Int ) = {
    reservations -= (ref_resa)
  }
  
  def calculerTarifReservation( ref_resa:Int ) = {
    val bat = reservations(ref_resa).no_bat
    val salle = reservations(ref_resa).no_salle
    val superficie = batiments(bat).salles(salle).superficie
    val typesallefact = batiments(bat).salles(salle).typeSalle.facteur_tarif
    val titre = reservations(ref_resa).demandeur.titre.facteur_tarif
    val origine = reservations(ref_resa).demandeur.origine.facteur_tarif
    val manif = reservations(ref_resa).manifestation.facteur_tarif
    val duree = reservations(ref_resa).duree.facteur_tarif
    reservations(ref_resa).tarif = superficie * typesallefact * manif * duree * titre * origine
  }
}