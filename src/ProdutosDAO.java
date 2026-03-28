/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
      String sql = "INSERT INTO leiloes (produto, valor, status) VALUES (?, ?, ?)";

    Connection conn = new conectaDAO().connectDB();

    try {
        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, produto.getNome());
        pstm.setInt(2, produto.getValor());
        pstm.setString(3, produto.getStatus());

        pstm.execute();

        pstm.close();

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage());
    }
   }
    
    public ArrayList<ProdutosDTO> listarProdutos() {

    ArrayList<ProdutosDTO> lista = new ArrayList<>();
    String sql = "SELECT * FROM leiloes";

    try {
        Connection conn = new conectaDAO().connectDB();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();

            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("produto"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status")); // ⚠️ minúsculo

            lista.add(produto);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar: " + e.getMessage());
    }

    return lista;
}

  public void venderProduto(int id) {

    String sql = "UPDATE leiloes SET status = 'Vendido' WHERE id = ?";

    try {
        Connection conn = new conectaDAO().connectDB();
        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, id);
        pstm.execute();

        JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender: " + e.getMessage());
    }
}
  
  public ArrayList<ProdutosDTO> listarVendidos() {

    ArrayList<ProdutosDTO> lista = new ArrayList<>();
    String sql = "SELECT * FROM leiloes WHERE status = 'Vendido'";

    try {
        Connection conn = new conectaDAO().connectDB();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();

            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("produto"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));

            lista.add(produto);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar vendidos: " + e.getMessage());
    }

    return lista;
}
    
    
    
        
}

