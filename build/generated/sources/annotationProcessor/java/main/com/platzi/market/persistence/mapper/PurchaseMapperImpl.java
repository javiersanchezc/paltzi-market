package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.Purchase;
import com.platzi.market.domain.PurchaseItem;
import com.platzi.market.persistence.entity.Compra;
import com.platzi.market.persistence.entity.ComprasProducto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-16T11:18:10-0500",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
@Component
public class PurchaseMapperImpl implements PurchaseMapper {

    @Autowired
    private PurchaseItemMapper purchaseItemMapper;

    @Override
    public Purchase toPurchase(Compra compra) {
        if ( compra == null ) {
            return null;
        }

        Purchase purchase = new Purchase();

        purchase.setDate( compra.getFecha() );
        purchase.setClientId( compra.getIdCliente() );
        if ( compra.getIdCompra() != null ) {
            purchase.setPurchaseId( compra.getIdCompra() );
        }
        purchase.setPaymentMethod( compra.getMedioPago() );
        purchase.setComment( compra.getComentario() );
        purchase.setState( compra.getEstado() );
        purchase.setItems( comprasProductoListToPurchaseItemList( compra.getProductos() ) );

        return purchase;
    }

    @Override
    public List<Purchase> toPurchases(List<Compra> compras) {
        if ( compras == null ) {
            return null;
        }

        List<Purchase> list = new ArrayList<Purchase>( compras.size() );
        for ( Compra compra : compras ) {
            list.add( toPurchase( compra ) );
        }

        return list;
    }

    @Override
    public Compra toCompra(Purchase purchase) {
        if ( purchase == null ) {
            return null;
        }

        Compra compra = new Compra();

        compra.setFecha( purchase.getDate() );
        compra.setEstado( purchase.getState() );
        compra.setIdCliente( purchase.getClientId() );
        compra.setIdCompra( purchase.getPurchaseId() );
        compra.setMedioPago( purchase.getPaymentMethod() );
        compra.setComentario( purchase.getComment() );
        compra.setProductos( purchaseItemListToComprasProductoList( purchase.getItems() ) );

        return compra;
    }

    protected List<PurchaseItem> comprasProductoListToPurchaseItemList(List<ComprasProducto> list) {
        if ( list == null ) {
            return null;
        }

        List<PurchaseItem> list1 = new ArrayList<PurchaseItem>( list.size() );
        for ( ComprasProducto comprasProducto : list ) {
            list1.add( purchaseItemMapper.toPurchaseItem( comprasProducto ) );
        }

        return list1;
    }

    protected List<ComprasProducto> purchaseItemListToComprasProductoList(List<PurchaseItem> list) {
        if ( list == null ) {
            return null;
        }

        List<ComprasProducto> list1 = new ArrayList<ComprasProducto>( list.size() );
        for ( PurchaseItem purchaseItem : list ) {
            list1.add( purchaseItemMapper.toComprasProducto( purchaseItem ) );
        }

        return list1;
    }
}
