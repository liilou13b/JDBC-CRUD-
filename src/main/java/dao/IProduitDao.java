package dao;

import entity.Produit;

import java.util.List;

/**
 * @author AliBouchatba
 *
 *  Interface IProduits Contients Contrats des Methodes
 *   à implement
 *
 */
public interface IProduitDao {

    /**
     *
     * @param mc  le Mot Clées de Recherche Designation
     * @return    La List Des Produits (Des tuples vers Des Instaces )
     */
    List<Produit> productByKeyword(String mc);

    /**
     *
     * @param p    Produit insérer dans La table Produits
     * @return      true cas succès
     *              false échec
     */
   Boolean addProduit(Produit p);

    /**
     *
     * @param id  Id de produit a chercher
     * @return    produit  cas si existe
     *            null
     */
    Produit findById(Long id);

    /**
     *
     * @param id  Id de produit a supprimmer
     * @return    True cas succès
     *            false échec
     */
    Boolean deleteByID(Long id);

    /**
     *
     * @param   produit Produit modifié
     * @return  True cas succès
     *          false échec
     */
     Boolean updateProduct(Produit produit);

}
