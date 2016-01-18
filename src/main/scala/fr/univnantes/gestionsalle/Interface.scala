import java.util.Date
import java.text.SimpleDateFormat

abstract class Interface {
  
  // Methodes batiment
  def ajouterBatiment( bat:Map[String,String] ) = {
    var adresse = new Adresse(bat("no"), bat("adresse"), bat("code"), bat("ville"))
    var batiment = new Batiment(bat("nom"), bat("no_bat").toInt, adresse)
    Systeme.ajouterBatiment(batiment)
  }
  
  def supprimerBatiment( no_bat:Int ) = {
    Systeme.supprimerBatiment(no_bat)
  }
  
  // Methodes salle
  def ajouterSalle( sal:Map[String,String] ) = {
    var type_salle = Systeme.typesSalle(sal("type_salle"))
    var salle = new Salle( sal("no_salle").toInt, sal("superficie").toInt, type_salle)
    Systeme.ajouterSalle(sal("no_bat").toInt, salle)
  }
  
  def supprimerSalle( no_bat:Int, no_salle:Int ) = {
    Systeme.supprimerSalle(no_bat, no_salle)
  }
  
  def ajouterMaterielFixe( mat:Map[String,String] ){
    var typemat = new TypeMateriel(mat("type_mat_nom"))
    var materiel = new MaterielFixe(mat("code_inv").toInt, typemat)
    Systeme.ajouterMaterielFixe(materiel, mat("no_bat").toInt, mat("no_salle").toInt)
  }
  
  def supprimerMaterielFixe( no_bat:Int, no_salle:Int, code_inv:Int ){
    Systeme.supprimerMaterielFixe(code_inv, no_bat, no_salle)
  }
  
  def ajouterTypeSalle( type_salle:Map[String,String] ){
    var typesalle = new TypeSalle(type_salle("nom_type"), type_salle("facteur_tarif").toDouble)
    Systeme.ajouterTypeSalle(typesalle)
  }
  
  def consulterTypeSalle( ) :Map[String,Double] = {
    var typesalle:Map[String,Double] = Map()
    Systeme.typesSalle.foreach(kv => typesalle += (kv._1 -> kv._2.facteur_tarif))
    return typesalle
  }
  
  // Methodes Materiel mobile
  def ajouterMaterielMobile( mat:Map[String,String] ){
    var typemat = new TypeMateriel(mat("type_mat_nom"))
    var materiel = new MaterielMobile(mat("code_inv").toInt, typemat)
    Systeme.ajouterMateriel(materiel)
  }
  
  def supprimerMaterielMobile( code_inv:Int ){
    Systeme.supprimerMateriel(code_inv)
  }
  
  // Methodes Demandeur
  def ajouterDemandeur( dem:Map[String,String] ){
    var titre = Systeme.titres(dem("nom_titre"))
    var origine = Systeme.origines(dem("nom_origine"))
    var adresse = new Adresse(dem("no"), dem("adresse"), dem("code"), dem("ville"))
    var demandeur = new Demandeur(dem("no_dem").toInt, dem("nom_dem"), titre, origine, adresse)
  }
  
  def supprimerDemandeur( no_dem:Int ){
    Systeme.supprimerDemandeur(no_dem)
  }
  
  def consulterDemandeurs():Map[Int,String] = {
    var demandeurs:Map[Int,String] = Map()
    Systeme.demandeurs.foreach(kv => demandeurs += (kv._1 -> kv._2.nom))
    return demandeurs
  }
  
  def consulterTitre():Map[String,Double] = {
    var titres:Map[String,Double] = Map()
    Systeme.titres.foreach(kv => titres += (kv._1 -> kv._2.facteur_tarif))
    return titres
  }
  
  def consulterOrigines():Map[String,Double] = {
    var origines:Map[String,Double] = Map()
    Systeme.origines.foreach(kv => origines += (kv._1 -> kv._2.facteur_tarif))
    return origines
  }
  
  // Methodes Reservation
  def ajouterReservation( resa:Map[String,String] ){
    var date = new SimpleDateFormat("dd-MM-yyyy").parse(resa("date"));
    var manifestation = Systeme.manifestations(resa("nom_manifestation"))
    var duree = Systeme.durees(resa("nom_duree"))
    var demandeur = Systeme.demandeurs(resa("no_dem").toInt)
    var reservation = new Reservation(resa("ref_resa").toInt, resa("no_bat").toInt, resa("no_salle").toInt, demandeur, 0.0, date, manifestation, duree)
  }
  
  def supprimerReservation( ref_resa:Int ){
    Systeme.supprimerReservation(ref_resa)
  }
  
  def consulterReservations():Map[Int,String] = {
    var resa:Map[Int,String] = Map()
    Systeme.reservations.foreach(kv => resa += (kv._1 -> kv._2.duree.nom))
    return resa
  }
  
  def consulterDurees():Map[String,Double] = {
    var durees:Map[String,Double] = Map()
    Systeme.durees.foreach(kv => durees += (kv._1 -> kv._2.facteur_tarif))
    return durees
  }
  
}