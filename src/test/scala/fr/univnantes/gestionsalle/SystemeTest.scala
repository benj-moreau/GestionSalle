import java.util.Date
import java.text.SimpleDateFormat
import org.scalatest.FunSuite

class SystemeTest extends FunSuite {
  test("Ajout d'un batiment") {
    val adresse = new Adresse("1", "test", "44300", "nantes")
    val batiment = new Batiment("test", 1, adresse)
    Systeme.ajouterBatiment(batiment)
    assert(Systeme.batiments(1).no_bat == 1)
  }
  test("l'ajout d'un batiment de meme identifiant doit le modifier") {
    val adresse = new Adresse("1", "test", "44300", "nantes")
    var batiment = new Batiment("test", 1, adresse)
    Systeme.ajouterBatiment(batiment)
    batiment = new Batiment("nom", 1, adresse)
    Systeme.ajouterBatiment(batiment)
    assert(Systeme.batiments(1).nom.equals("nom"))
  }
  test("supression d'un batiment") {
    val adresse = new Adresse("1", "test", "44300", "nantes")
    var batiment = new Batiment("test", 1, adresse)
    Systeme.ajouterBatiment(batiment)
    Systeme.supprimerBatiment(1)
    intercept[Exception] { Systeme.batiments(1) }
  }
  //tests identiques pour : salles types durees.
  
  test("ajout d'une reservation et verification du tarif"){
    var date = new SimpleDateFormat("dd-MM-yyyy").parse("13-12-2015");
    var manifestation = new Manifestation("manif1",1.0)
    var duree = new Duree("matin",1.0,new SimpleDateFormat("H").parse("8"),new SimpleDateFormat("H").parse("12"))
    var adresse = new Adresse("1", "test", "44300", "nantes")
    var titre = new Titre("truc",1.0)
    var origine = new Origine("truc",1.0)
    var demandeur = new Demandeur(1, "benjamin", titre, origine, adresse)
    var batiment = new Batiment("test", 1, adresse)
    Systeme.ajouterBatiment(batiment)
    var salle = new Salle(1,20, new TypeSalle("type",1.0))
    Systeme.ajouterSalle(1,salle)
    var reservation = new Reservation(1,1,1, demandeur, 0.0, date, manifestation, duree)
    Systeme.ajouterReservation(reservation)
    assert(Systeme.reservations(1).tarif == 20)
  }
  test("doit refuser une reservations concomitantes"){
    var date = new SimpleDateFormat("dd-MM-yyyy").parse("13-12-2015");
    var manifestation = new Manifestation("manif1",1.0)
    var duree = new Duree("matin",1.0,new SimpleDateFormat("H").parse("8"),new SimpleDateFormat("H").parse("12"))
    var adresse = new Adresse("1", "test", "44300", "nantes")
    var titre = new Titre("truc",1.0)
    var origine = new Origine("truc",1.0)
    var demandeur = new Demandeur(1, "benjamin", titre, origine, adresse)
    var batiment = new Batiment("test", 1, adresse)
    Systeme.ajouterBatiment(batiment)
    var salle = new Salle(1,20, new TypeSalle("type",1.0))
    Systeme.ajouterSalle(1,salle)
    var reservation = new Reservation(1,1,1, demandeur, 0.0, date, manifestation, duree)
    var reservation2 = new Reservation(2,1,1, demandeur, 0.0, date, manifestation, duree)
    Systeme.ajouterReservation(reservation)
    assert(Systeme.ajouterReservation(reservation2)==false)   
  }
}