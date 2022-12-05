/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author User
 */

public class Produtos {
    
    boolean suspenso;
    String descricao;
    String preco;
    String estoque;
    String uriImage;
    String detalhe;
    String idProduto;
    
    public Produtos(){}

    public Produtos(String uriImage, String detalhe, String idProduto, boolean suspenso, String descricao, String preco, String estoque) {
        this.suspenso = suspenso;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.detalhe = detalhe;
        this.uriImage = uriImage;
        this.idProduto = idProduto;
    }

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }
    
    

    public boolean isSuspenso() {
        return suspenso;
    }

    public void setSuspenso(boolean suspenso) {
        this.suspenso = suspenso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getEstoque() {
        return estoque;
    }

    public void setEstoque(String estoque) {
        this.estoque = estoque;
    }
    
    
    
}
