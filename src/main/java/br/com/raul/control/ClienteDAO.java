/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.raul.control;


import br.com.raul.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author raul.venson
 */
public class ClienteDAO {

    public void save(Cliente cliente) {
        String sql = "insert into cliente (nome, tipopessoa, cep, endereco, numero, complemento, bairro, estado, cidade, email, telefone, situacao) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
           conn = Conexao.getConexao();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getTipopessoa());
            pstm.setString(3, cliente.getCep());
            pstm.setString(4, cliente.getEndereco());
            pstm.setString(5, cliente.getNumero());
            pstm.setString(6, cliente.getComplemento());
            pstm.setString(7, cliente.getBairro());
            pstm.setString(8, cliente.getEstado());
            pstm.setString(9, cliente.getCidade());
            pstm.setString(10, cliente.getEmail());
            pstm.setString(11, cliente.getTelefone());
            pstm.setString(12, cliente.getSituacao());

            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    pstm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Cliente> read() {
        
        Connection conn = Conexao.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cliente";
        
        List<Cliente> estados = new ArrayList<>();

        try {
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setCod(rs.getInt("cod"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTipopessoa(rs.getString("tipopessoa"));
                cliente.setCep(rs.getString("cep"));
                cliente.setCep(rs.getString("endereco"));
                cliente.setCep(rs.getString("numero"));
                cliente.setCep(rs.getString("complemento"));
                cliente.setCep(rs.getString("bairro"));
                cliente.setCep(rs.getString("estado"));
                cliente.setCep(rs.getString("cidade"));
                cliente.setCep(rs.getString("email"));
                cliente.setCep(rs.getString("telefone"));
                cliente.setCep(rs.getString("situacao"));
                estados.add(cliente);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(conn, pstm, rs);
        }
        
        
        return estados;
        
    }

    public void update(Cliente cliente) {
        String sql = "update estados set nome = ?, tipopessoa = ? cep = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?, estado = ?, cidade = ?, email = ?, telefone = ?, situacao = ?  WHERE cod = ?";

        Connection conn = Conexao.getConexao();
        PreparedStatement pstm = null;

        try {

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getTipopessoa());
            pstm.setString(3, cliente.getCep());
            pstm.setString(4, cliente.getEndereco());
            pstm.setString(5, cliente.getNumero());
            pstm.setString(6, cliente.getComplemento());
            pstm.setString(7, cliente.getBairro());
            pstm.setString(8, cliente.getEstado());
            pstm.setString(9, cliente.getCidade());
            pstm.setString(10, cliente.getEmail());
            pstm.setString(11, cliente.getTelefone());
            pstm.setString(12, cliente.getSituacao());
            pstm.setInt(13, cliente.getCod());
            

            pstm.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e);
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    pstm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Cliente cliente) {
        String sql = "delete from cliente where cod = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.getConexao();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, cliente.getCod());

            pstm.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!");
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    pstm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
