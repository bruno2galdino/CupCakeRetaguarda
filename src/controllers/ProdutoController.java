/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.bean.Produtos;
import model.dao.ProdutosDAO;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ProdutoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtUriImagem;

    @FXML

    private TableView<Produtos> tableViewProdutos;

    @FXML
    private TableColumn<Produtos, String> tableColumnPrecoVenda;

    @FXML
    private TableColumn<Produtos, String> tableColumnDescricao;

    @FXML
    private TableColumn<Produtos, String> tableColumnEstoque;

    @FXML
    private TableColumn<Produtos, Boolean> tableColumnSuspenso;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtEstoque;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnNovo;

    @FXML
    private Button btnFechar;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnCancelar;

    @FXML
    private RadioButton radioButtonSuspenso;

    @FXML
    private ImageView imgProduto;

    @FXML
    private TextArea txtDetalhes;

    @FXML
    private TextField txtPrecoVenda;

    @FXML
    private Button btnSelecionaImagem;

    @FXML
    private AnchorPane anchorPaneProduto;

    public static AnchorPane anchorPai = null;
    
    boolean edit = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarProdutos();

        btnFechar.setOnAction(eh -> {
            anchorPai.getChildren().clear();
        });

        btnNovo.setOnAction(eh -> {
            anchorPaneProduto.setVisible(true);
            clearCadastro();            
        });
        
        btnCancelar.setOnAction(eh -> {
            anchorPaneProduto.setVisible(false);
            clearCadastro();
        });
        
        btnSelecionaImagem.setOnAction(eh ->{
            String imagem = getImagem();
            txtUriImagem.setText("http://192.168.2.103/imagens/produtos/" + imagem.substring(36,imagem.length()));
        });
        
        btnEditar.setOnAction(eh -> {
            anchorPaneProduto.setVisible(true);
            txtDescricao.setText(tableViewProdutos.getSelectionModel().getSelectedItem().getDescricao());
            txtDetalhes.setText(tableViewProdutos.getSelectionModel().getSelectedItem().getDetalhe());
            txtEstoque.setText(tableViewProdutos.getSelectionModel().getSelectedItem().getEstoque());
            txtPrecoVenda.setText(tableViewProdutos.getSelectionModel().getSelectedItem().getPreco().substring(3, 
                    tableViewProdutos.getSelectionModel().getSelectedItem().getPreco().length()));
            txtUriImagem.setText(tableViewProdutos.getSelectionModel().getSelectedItem().getUriImage());
            edit = true;
            imgProduto.setImage(new Image(getDirImagem(tableViewProdutos.getSelectionModel().
                    getSelectedItem().getUriImage()).toURI().toString()));
            System.out.println(getDirImagem(tableViewProdutos.getSelectionModel().
                    getSelectedItem().getUriImage()).toURI().toString());
            
        });
        
        btnSalvar.setOnAction(eh -> {
            if(edit){
                ProdutosDAO produtosDAO = new ProdutosDAO();
                Produtos produto = new Produtos();
                produto.setIdProduto(tableViewProdutos.getSelectionModel().getSelectedItem().getIdProduto());
                produto.setDescricao(txtDescricao.getText());
                produto.setDetalhe(txtDetalhes.getText());
                produto.setEstoque(txtEstoque.getText());
                produto.setPreco(txtPrecoVenda.getText());
                produto.setSuspenso(radioButtonSuspenso.isSelected());
                produto.setUriImage(txtUriImagem.getText());
                produtosDAO.editarProduto(produto);
                
                anchorPaneProduto.setVisible(false);
                carregarProdutos();                
            }else{
                ProdutosDAO produtosDAO = new ProdutosDAO();
                Produtos produto = new Produtos();
                produto.setDescricao(txtDescricao.getText());
                produto.setDetalhe(txtDetalhes.getText());
                produto.setEstoque(txtEstoque.getText());
                produto.setPreco(txtPrecoVenda.getText());
                produto.setSuspenso(radioButtonSuspenso.isSelected());
                produto.setUriImage(txtUriImagem.getText());
                produtosDAO.salvarProduto(produto);
                
                anchorPaneProduto.setVisible(false);
                carregarProdutos();
            }
        });

    }

    public void clearCadastro() {
        txtDescricao.setText("");
        txtDetalhes.setText("");
        txtEstoque.setText("");
        txtPrecoVenda.setText("");
        txtUriImagem.setText("");
        radioButtonSuspenso.setSelected(false);
        edit = false;
    }

    public void carregarProdutos() {
        tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tableColumnPrecoVenda.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tableColumnEstoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));
        tableColumnSuspenso.setCellValueFactory(new PropertyValueFactory<Produtos, Boolean>("suspenso"));

        tableColumnSuspenso.setCellFactory(CheckBoxTableCell.forTableColumn(tableColumnSuspenso));

        ProdutosDAO produtosDAO = new ProdutosDAO();
        List<Produtos> produtos = produtosDAO.listaProduto();

        ObservableList<Produtos> observableList = FXCollections.observableArrayList();
        observableList.addAll(produtos);

        tableViewProdutos.setItems(observableList);

        Map<Produtos, BooleanProperty> checkedItens = new HashMap<>();

        tableColumnSuspenso.setCellFactory(CheckBoxTableCell.forTableColumn(i -> checkedItens.computeIfAbsent(
                tableViewProdutos.getItems().get(i), p -> new SimpleBooleanProperty())));

    }

    public static void getPai(AnchorPane pai) {
        anchorPai = pai;
    }
    
    public String getImagem(){
        String dir = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Imagem");
        fileChooser.setInitialDirectory(new File("C:\\Apache24\\htdocs\\imagens\\produtos\\"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagem", "*.jpg", "*.png", "*.gif")
        );
        
        File file = fileChooser.showOpenDialog(btnSelecionaImagem.getScene().getWindow());
        
        if(file != null){
            dir = file.getPath();
            imgProduto.setImage(new Image(file.toURI().toString()));
        }
        
        return dir;
    }
    
    public File getDirImagem(String uri){
        return new File("C:\\Apache24\\htdocs\\imagens\\produtos\\"+uri.substring(38, uri.length()));
    }

}
