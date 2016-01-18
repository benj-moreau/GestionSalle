import java.util.Date

class Reservation(var ref_resa:Int, var no_bat:Int,var no_salle:Int, var demandeur:Demandeur, var tarif:Double, var date:Date, var manifestation:Manifestation, var duree:Duree) {
  var materiels: Map[Int,MaterielMobile] = Map()
}