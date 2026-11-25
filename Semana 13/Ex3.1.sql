-- SELECT categoria.nome AS nome_cat,
--        produto.preco AS preco_prod,
--        produto.nome AS nome_prod
-- FROM produto
-- INNER JOIN categoria
--   ON produto.id_categoria = categoria.id_categoria
-- WHERE produto.id_categoria IS NULL;

SELECT categoria.nome AS nome_cat,
       produto.preco AS preco_prod,
       produto.nome AS nome_prod
FROM categoria
LEFT JOIN produto
  ON produto.id_categoria = categoria.id_categoria
WHERE produto.id_categoria IS NULL;

/*
  A diferença existe no caso de o  produto estar sem categoria:
  o INNER JOIN o excluiria, e o LEFT JOIN o mostraria com
  nome_cat = NULL.
  
*/