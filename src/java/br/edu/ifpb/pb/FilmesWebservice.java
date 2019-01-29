/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pb;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.ws.BindingType;

/**
 *
 * @author yuria
 */
@WebService(serviceName = "FilmesWebservice")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class FilmesWebservice {

    private EntityManagerFactory emf;
    private EntityManager em;
    
    public FilmesWebservice(){
//        emf = Persistence.createEntityManagerFactory("Projeto3PU");
//        em = emf.createEntityManager();
    }
        
    @WebMethod(operationName = "cadastrar")
    public String cadastrar(@WebParam(name = "titulo") String titulo,
                            @WebParam(name = "diretor") String diretor,
                            @WebParam(name = "estudio") String estudio,
                            @WebParam(name = "genero") String genero,
                            @WebParam(name = "lancamento") String lancamento) {
        List<String> errors = new ArrayList<String>();
        try{            
            if(titulo == null || titulo == ""){
                errors.add("Parâmetro titulo é obrigatório");
            }            
            
            if(diretor == null || diretor == ""){
                errors.add("Parâmetro diretor é obrigatório");
            }
            
            if(estudio == null || estudio == ""){
                errors.add("Parâmetro estudio é obrigatório");
            }
            
            if(genero == null || genero == ""){
                errors.add("Parâmetro genero é obrigatório");
            }
            
            if(lancamento == null || lancamento == ""){
                errors.add("Parâmetro lancamento é obrigatório");
            }
            
            if(errors.isEmpty()){
                emf = Persistence.createEntityManagerFactory("Projeto3PU");
                em = emf.createEntityManager();
        
                em.getTransaction().begin();
                em.persist(new Filme(titulo, diretor, estudio, genero, lancamento));
                em.flush();
                em.getTransaction().commit();
                //em.close();
                errors.clear();
                return "Filme cadastrado!";
            }
            
        }catch(NumberFormatException e){
            errors.add("O parâmetro Codigo precisa ser um numero inteiro");
        }
        
        String str_error = "";
        for(String error : errors){
            str_error += error + "\n";
        }
        
        return str_error;
        
    }
    
    @WebMethod(operationName = "alterarTitulo")
    public String atualizar(@WebParam(name = "codigo") String codigo,
                            @WebParam(name = "titulo") String titulo){
        
        try{
            emf = Persistence.createEntityManagerFactory("Projeto3PU");
            em = emf.createEntityManager();  
            
            Filme f = em.find(Filme.class, Integer.parseInt(codigo)); //procura um filme de acordo com o codigo
            
            if(f != null){ 
                if(titulo != null){
                    f.setTitulo(titulo);
                }     
                try { 
                    em.getTransaction().begin();
                    em.merge(f);
                    em.flush();
                    em.getTransaction().commit(); 
                } catch (Exception e) {
                    return "Erro ao salvar no banco";
                }
                
                return "Filme atualizado!";
            }
            
        }catch(NumberFormatException e){ 
            return "O parâmetro Codigo precisa ser um numero inteiro";
        }
        
        return "Codigo não encontrado!";
    }
    
    @WebMethod(operationName = "consultarPorDiretor")
    public Filme consultarPorDiretor(@WebParam(name = "diretor")String diretor){
        
        emf = Persistence.createEntityManagerFactory("Projeto3PU");
        em = emf.createEntityManager();

        Filme filme = (Filme) em.createQuery("SELECT l from Filme l where l.diretor = :diretor")
            .setParameter("diretor", diretor)
            .getSingleResult();
        return filme;
        
    }
    
    @WebMethod(operationName = "consultarPorTitulo")
    public Filme consultarPorTitulo(@WebParam(name = "titulo")String titulo){
                
        emf = Persistence.createEntityManagerFactory("Projeto3PU");
        em = emf.createEntityManager();

        Filme filme = (Filme) em.createQuery("SELECT l from Filme l where l.titulo = :titulo")
            .setParameter("titulo", titulo)
            .getSingleResult();
        return filme;
        
    }
    
    @WebMethod(operationName = "consultarPorGenero")
    public Filme consultarPorGenero(@WebParam(name = "genero")String genero){
        
        emf = Persistence.createEntityManagerFactory("Projeto3PU");
        em = emf.createEntityManager();

        Filme filme = (Filme) em.createQuery("SELECT l from Filme l where l.genero = :genero")
            .setParameter("genero", genero)
            .getSingleResult();
        return filme;
        
    }
    
    @WebMethod(operationName = "consultarPorLancamento")
    public Filme consultarPorLancamento(@WebParam(name = "lancamento")String lancamento){
        
        emf = Persistence.createEntityManagerFactory("Projeto3PU");
        em = emf.createEntityManager();

        Filme filme = (Filme) em.createQuery("SELECT l from Filme l where l.lancamento = :lancamento")
            .setParameter("lancamento", lancamento)
            .getSingleResult();
        return filme;
        
    }
    
    @WebMethod(operationName = "remover")
    public Filme remover(@WebParam(name = "codigo")String codigo){
        
        Filme filme = null;
        try{
            
            if(codigo == null){
                return filme;
            }
            
            emf = Persistence.createEntityManagerFactory("Projeto3PU");
            em = emf.createEntityManager();
                
            filme = em.find(Filme.class, Integer.parseInt(codigo));
            
            if(filme != null){
                em.getTransaction().begin();
                em.remove(filme);
                em.getTransaction().commit();
                return filme;
            }
            
        }catch(Exception e){ 
        }
        return filme;
    }
    
    private String print(String m){
        System.out.println(m);
        return m;
    }
}
