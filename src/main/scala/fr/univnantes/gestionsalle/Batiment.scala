class Batiment(var nom:String, var no_bat:Int, var adresse:Adresse) {
  var salles: Map[Int,Salle] = Map()
  
  def ajouterSalle( salle:Salle ) {
    salles += (salle.no_salle -> salle)
  }
  
  def supprimerSalle( no_salle:Int ) {
    salles -= (no_salle)
  }

  
  def ajouterMateriel( materiel:MaterielFixe, no_salle:Int )  = {
    salles(no_salle).ajouterMateriel(materiel)
  }
  
  def supprimerMateriel( code_inv:Int, no_salle:Int) = {
    salles(no_salle).supprimerMateriel(code_inv)
  }
  
}