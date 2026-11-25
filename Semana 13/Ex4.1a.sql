SELECT pedido.id_pedido,
       cliente.nome
FROM cliente
INNER JOIN pedido
  ON cliente.id_cliente = pedido.id_cliente