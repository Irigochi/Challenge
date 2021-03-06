package newpackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
/**
 *
 * @author Kouta
 */
public class challenge3 {
    public static void main(String[] args) {        
           
           Connection con = null;                    
           try{
               Class.forName("com.mysql.jdbc.Driver").newInstance();
               con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Challenge_db","root","kirara0918");
               System.out.println("mysqlに接続できました");
               
 
               //課題3:前回の課題1で作成したテーブルからSELECT*により全件取得し、画面に取得した全情報を表示してください              
               /*
               Statement stm = con.createStatement();
               String sql = "select*from profiles";
               ResultSet rs = stm.executeQuery(sql);
               //上記のやり方は処理に時間がかかる(?)
               */
               
               String sql = "select*from profiles";
               PreparedStatement ps = con.prepareStatement(sql);
               ResultSet rs = ps.executeQuery();

               while(rs.next()){
                   int id = rs.getInt("profilesID");
                   String name = rs.getString("name");
                   String tell = rs.getString("tell");
                   int age = rs.getInt("age");
                   String birth = rs.getString("birthday");
                   
                   System.out.println("取得結果");
                   System.out.println("ID:"+id+"/名前:"+name+
                           "/電話番号:"+tell+"/年齢:"+age+"/生年月日:"+birth);
               }
                                            
               rs.close();
               ps.close();
               con.close();
               
               
           }catch(InstantiationException | IllegalAccessException | ClassNotFoundException e){
               System.out.println("JDBCドライバのロードに失敗しました");
           }catch(SQLException e){
               System.out.println("mysqlに接続できませんでした");
           }finally{
               if(con != null){
                   try{
                       con.close();
                   }catch(SQLException e){
                       System.out.println("mysqlのクローズに失敗しました");
                   }
               }
           }
       }
}
