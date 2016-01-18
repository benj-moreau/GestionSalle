import java.util.Date

class Duree(var nom:String, var facteur_tarif:Double, var heure_debut:Date, var heure_fin:Date) {
  
  def concomitant(duree:Duree) : Boolean = {
    if((heure_debut.before(duree.heure_debut) || heure_debut.equals(duree.heure_debut) ) && ( duree.heure_debut.before(heure_fin) || duree.heure_debut.equals(heure_fin))){
      return true
    }else if((heure_debut.before(duree.heure_fin) || heure_debut.equals(duree.heure_fin)) && duree.heure_fin.before(heure_fin) || duree.heure_fin.equals(heure_fin)){
      return true
    }
    return false
  }
}