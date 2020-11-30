package dao;

import entity.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDao implements IProduitDao {

    //  Database Url
    static  final private   String DB_URL ="jdbc:mysql://localhost:3308/db_gsproduits?autoReconnect=true&useSSL=false";
    //  Database Username  Password
    static final private String USERN = "root";
    static final private String PASS = "";
    static private   Connection open() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");   // charger  Pilot JDBC
        Connection con = DriverManager.getConnection  // Etablir la conection avec BD
                (DB_URL,USERN,PASS);
        return con;

    }




    @Override
    public List<Produit> productByKeyword(String mc) {
        //##### Int
        List<Produit> produits = new ArrayList<>();

        // ******* la Methode Forname Contenir exception ClassNOtFound
        try {
            ProduitDao.open();

            PreparedStatement ps = ProduitDao.open().prepareStatement("select * from produit p where p.designation like ?");
            ps.setString(1,"%"+mc+"%");

            ResultSet rs = ps.executeQuery();  // exécuter La req est stocké dans Objet de Class ResultSet

            while (rs.next()){
                // Mapping Objet Relationel --> (parcouri les tuples est transférer chaque tuple en instance
                Produit p = new Produit();
                p.setId(rs.getLong("id"));
                p.setDesignation(rs.getString("designation"));
                p.setPrix(rs.getDouble("prix"));
                p.setQuantite(rs.getInt("quantite"));

                produits.add(p);

            }
            // Fermer La conection avec la BD
            rs.close();
            ps.close();
            ProduitDao.open().close();
            return  produits;


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ClassNotFound");
                return  null;
        } catch (SQLException e) {
            System.out.println("SQLEx");
            e.printStackTrace();
             return  null;
        }




    }

    @Override
    public Boolean addProduit(Produit p) {
            boolean succ=false;
        try {
            if (p!=null) {
                PreparedStatement ps = ProduitDao.open().prepareStatement("insert into  produit (designation,prix,quantite) value (?,?,?)");
                ps.setString(1, p.getDesignation());
                ps.setDouble(2, p.getPrix());
                ps.setInt(3, p.getQuantite());

                if (ps.executeUpdate() != 0)
                    succ = true;

                //
                ps.close();
                ProduitDao.open().close();
            }
           return  succ;

        } catch (SQLException e) {
            e.printStackTrace();
            return succ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return succ;
        }



    }

    @Override
    public Produit findById(Long id) {
            Produit p = null;
        try {
            PreparedStatement ps= ProduitDao.open().prepareStatement("select * from  produit where id = ?");
            ps.setLong(1,id);

            ResultSet rs =ps.executeQuery();
            if (rs.next()) {
                p=new Produit();
                p.setId(id);
                p.setDesignation(rs.getString("designation"));
                p.setPrix(rs.getDouble("prix"));
                p.setQuantite(rs.getInt("quantite"));

            }

            return  p;

        } catch (SQLException e) {
            e.printStackTrace();
            return p;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return p;
        }


    }

    @Override
    public Boolean deleteByID(Long id) {

        Boolean succ= false;
        try {
            PreparedStatement ps =ProduitDao.open().prepareStatement("delete  from produit where id = ?");
            ps.setLong(1,id);
            if (ps.executeUpdate()!=0)
                succ=true;
            return succ;
        } catch (SQLException e) {
            e.printStackTrace();
            return succ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return succ;
        }



    }

    @Override
    public Boolean updateProduct(Produit produit) {
            Boolean succ =false;
        try {
            if (this.findById(produit.getId())!=null) {

                PreparedStatement ps = ProduitDao.open().prepareStatement("update  produit set designation=? , prix=?, quantite=? where id = ? ");
                ps.setString(1, produit.getDesignation());
                ps.setDouble(2, produit.getPrix());
                ps.setInt(3, produit.getQuantite());
                ps.setLong(4,produit.getId());

                if (ps.executeUpdate()!=0)
                    succ=true;

            }
            return  succ;

        } catch (SQLException e) {
            e.printStackTrace();
            return succ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return succ;
        }

    }
}
