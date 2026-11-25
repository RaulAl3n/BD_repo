SELECT categoria.nome AS nome_cat,
       produto.nome   AS nome_prod
FROM categoria
LEFT JOIN produto
  ON produto.id_categoria = categoria.id_categoria
ORDER BY categoria.nome;