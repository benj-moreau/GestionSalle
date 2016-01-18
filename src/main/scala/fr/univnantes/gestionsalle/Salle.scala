class Salle(var no_salle:Int, var superficie:Int, var typeSalle:TypeSalle) {
  var materiel_fixe: Map[Int,MaterielFixe] = Map()
  
  def ajouterMateriel( materiel:MaterielFixe ) = {
    materiel_fixe += (materiel.code_inv -> materiel)
  }
  
  def supprimerMateriel( code_inv:Int ) = {
    materiel_fixe -= (code_inv)
  }
}