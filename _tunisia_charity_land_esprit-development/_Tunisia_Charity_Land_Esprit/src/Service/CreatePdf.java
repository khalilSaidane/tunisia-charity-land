package Service;

import Entity.Article;
import Utils.DataSource;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreatePdf {

    private static Connection connection;

    public static void convertToPdf() throws SQLException, DocumentException, FileNotFoundException {

        connection = DataSource.getInstance().getConnection();
        

        ArrayList<Article> tab = new ArrayList<>();
        String req = "SELECT * FROM `article`";

        Statement ste = connection.createStatement();
        ResultSet result = ste.executeQuery(req);
        while (result.next()) {
            int id = result.getInt(1);
            int id_auteur = result.getInt(2);
            Date date = result.getDate(3);

            String titre = result.getString(4);
            String contenu = result.getString(5);
            String image_url = result.getString(6);

            //in every etiration we create a new instance 
            Article a = new Article(id, id_auteur, date, titre, contenu, image_url);

            tab.add(a);}
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream("articles.pdf"));
                document.open();
                int i;
              for(i=0;i<tab.size();i++){
                   Paragraph p1 = new Paragraph(tab.get(i).toString());

                    document.add(p1);
              }
                   
                 
                
            }catch (FileNotFoundException ex) {
                    Logger.getLogger(CreatePdf.class.getName()).log(Level.SEVERE, null, ex);
                }

            

             document.close();
        }

    
    

    public static void main(String[] args) throws FileNotFoundException {
        try {
            CreatePdf.convertToPdf();
        } catch (SQLException | DocumentException ex) {
            Logger.getLogger(CreatePdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
