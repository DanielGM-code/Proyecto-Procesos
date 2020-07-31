/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadegestióndeplanesdecurso.datos.dao;

import java.util.List;


/**
 *
 * @author angel
 */
public interface Dao {
    public abstract List<Object> getAll();
    public abstract Object getByID(String id);
    public abstract void save(Object object);
    public abstract void delete (Object object);
}
