import java.util.Date
import java.text.SimpleDateFormat
import org.scalatest.FunSuite

class BatimentTest extends FunSuite {
  test("Ajout d'une salle dans le batiment") {
    var salle = new Salle(1,20, new TypeSalle("type",1.0))
    val adresse = new Adresse("1", "test", "44300", "nantes")
    val batiment = new Batiment("test", 1, adresse)
    batiment.ajouterSalle(salle)
    assert(batiment.salles(1).no_salle == 1)
  }
  test("supression d'une salle du batiment") {
    var salle = new Salle(1,20, new TypeSalle("type",1.0))
    val adresse = new Adresse("1", "test", "44300", "nantes")
    val batiment = new Batiment("test", 1, adresse)
    batiment.ajouterSalle(salle)
    batiment.supprimerSalle(1)
    intercept[Exception] { batiment.salles(1) }
  }
}