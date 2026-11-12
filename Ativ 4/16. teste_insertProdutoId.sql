INSERT INTO produto(nome,preco,id_categoria,descricao)
  VALUES('Mouse sem fio', 89.90, 9999, 'Mouse óptico sem fio')

  /* 
    17)
      Não deveria permirtir a inserção pois supondo que nao exista id 9999, isso ira violar a regra de chave estrangeira.
      Saída:
        Failed to run sql query: ERROR:  23503: insert or update on table "produto" violates foreign key constraint "fk_id_categoria"
        DETAIL:  Key (id_categoria)=(9999) is not present in table "categoria".

  */