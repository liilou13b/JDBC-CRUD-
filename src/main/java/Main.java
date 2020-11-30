import dao.IProduitDao;
import dao.ProduitDao;
import entity.Produit;

import java.util.ArrayList;
import java.util.List;

/**
 *   Class Main Pour Tester Les fonctionalité
 *      JDBC Sans FrameWork Mapping objetc relationel
 *
 */
public class Main {

    public static void main(String[] args) {

        IProduitDao dao = new ProduitDao();



        System.out.println("//****** Insertion Du Produit ");
        Produit p= new Produit(2L,"Mac2017",8_000d,60);
        dao.addProduit(p);

        System.out.println("//******Find Product");
        Produit produitChercher=dao.findById(11L);
        System.out.println(produitChercher);

        System.out.println("//****** delete product");
        if (dao.deleteByID(14L)){
            System.out.println("produit bien supprimer ");
        }
        else System.out.println("suppresion Echec");


        System.out.println("//****** update product");
       Produit produitUpd= new Produit(11L,"toshiba 895",5_000d,60);
        Produit produitUpd2= new Produit(81L,"Test Update",5_000d,60);
       if (dao.updateProduct(produitUpd)){
           System.out.println("Bien Moddifer ");
       }else System.out.println("Echec Modification");

        if (dao.updateProduct(produitUpd2)){
            System.out.println("Bien Moddifer ");
        }else System.out.println("Echec Modification");


        System.out.println("//******List des produit avec recherche par Mot clé");
        List<Produit> produits = new ArrayList<>(dao.productByKeyword("HP"));
        produits.forEach(System.out::println);





    }
}
