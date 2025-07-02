package com.example.demo.shared.interfaces;

public interface AppService<T, ID> {
    
    
    <S extends T> T create(S createEntityDto);
    
    
}
