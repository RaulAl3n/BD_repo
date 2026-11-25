SELECT categoria.nome AS nome_cat
FROM categoria
LEFT JOIN produto
  ON produto.id_categoria = categoria.id_categoria
WHERE produto.id_categoria IS NULL;