package com.myproject.agrolink.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.agrolink.dao.OrderItemRepository;
import com.myproject.agrolink.dao.OrderRepository;
import com.myproject.agrolink.dao.OrderStatusNotifRepository;
import com.myproject.agrolink.dao.OrderStatusRepository;
import com.myproject.agrolink.dao.AdminRepository;
import com.myproject.agrolink.dao.ClientRepository;
import com.myproject.agrolink.dao.FarmRepository;
import com.myproject.agrolink.dao.FarmUserRepository;
import com.myproject.agrolink.dao.FundsDistribRepository;
import com.myproject.agrolink.dao.ProductRepository;
import com.myproject.agrolink.entity.Order;
import com.myproject.agrolink.entity.OrderItem;
import com.myproject.agrolink.entity.OrderStatus;
import com.myproject.agrolink.entity.OrderStatusNotif;
import com.myproject.agrolink.entity.Admin;
import com.myproject.agrolink.entity.Client;
import com.myproject.agrolink.entity.Farm;
import com.myproject.agrolink.entity.FarmUser;
import com.myproject.agrolink.entity.FundsDistrib;
import com.myproject.agrolink.entity.Product;
import com.myproject.agrolink.requestmodel.FundsDistribRequest;
import com.myproject.agrolink.requestmodel.ModifyOrderRequest;
import com.myproject.agrolink.requestmodel.ModifyOrderStatusRequest;
import com.myproject.agrolink.requestmodel.OrderItemRequest;
import com.myproject.agrolink.requestmodel.OrderRequest;
import com.myproject.agrolink.requestmodel.OrderStatusNotifRequest;
import com.myproject.agrolink.requestmodel.OrderStatusRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    private OrderRepository orderRepository;

    private OrderItemRepository orderItemRepository;

    private ProductRepository productRepository;

    private ClientRepository clientRepository;

    private FarmRepository farmRepository;

    private AdminRepository adminRepository;

    private FarmUserRepository farmUserRepository;

    private OrderStatusRepository orderStatusRepository;

    private OrderStatusNotifRepository orderStatusNotifRepository;

    private FundsDistribRepository fundsDistribRepository;

    public OrderService(AdminRepository adminRepository, OrderStatusNotifRepository orderStatusNotifRepository,
            FarmUserRepository farmUserRepository, FundsDistribRepository fundsDistribRepository,
            OrderRepository orderRepository, OrderItemRepository orderItemRepository,
            ProductRepository productRepository, ClientRepository clientRepository, FarmRepository farmRepository,
            OrderStatusRepository orderStatusRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.clientRepository = clientRepository;
        // this.paymentMethodRepository = paymentMethodRepository;
        this.farmRepository = farmRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.fundsDistribRepository = fundsDistribRepository;
        this.farmUserRepository = farmUserRepository;
        this.orderStatusNotifRepository = orderStatusNotifRepository;
        this.adminRepository = adminRepository;
    }

    public Order getOrderById(Integer orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new Exception("Order with id " + orderId + " not found");
        }

    }

    public Order addOrder(OrderRequest orderRequest) throws Exception {
        Optional<Client> client = clientRepository.findById(orderRequest.getClientId());

        if (!client.isPresent()) {
            throw new Exception("Client doesn't exist");
        }
        
        Order order = new Order();

        // Client
        order.setClient(client.get());

        // Phone
        order.setPhone(orderRequest.getPhone());

        // Creation date time
        order.setCreationDt(LocalDateTime.now());

        // Products value
        order.setProductsValue(orderRequest.getProductsValue());

        // Delivery Price
        order.setDeliveryPrice(orderRequest.getDeliveryFee());

        // Payment value
        order.setPaymentValue(orderRequest.getDeliveryFee().add(orderRequest.getProductsValue()));

        // Payment type
        order.setPaymentType(1);

        // Locality
        order.setLocality(orderRequest.getLocality());

        // Address
        order.setAddress(orderRequest.getAddress());

        order.setCompleted(0);

        orderRepository.save(order);

        return order;
    }

    public void modifyOrder(ModifyOrderRequest modifyOrderRequest) throws Exception {
        Order order = getOrderById(modifyOrderRequest.getOrderId());

        // Optional<Admin> admin = adminRepository.findById(modifyOrderRequest.getAdminId());

        // order.setAdmin(admin.get());

         if(modifyOrderRequest.getCompleted()!=null && modifyOrderRequest.getCompleted().isPresent()){
            order.setCompleted(modifyOrderRequest.getCompleted().get());
        }
    }

    public OrderItem addOrderItem(OrderItemRequest orderItemRequest) throws Exception {

        Order order = getOrderById(orderItemRequest.getOrderId());

        Optional<Product> product = productRepository.findById(orderItemRequest.getProductId());

        if (!product.isPresent()) {
            throw new Exception("Product doesn't exist");
        }

        // Verificare daca produsul a fost deja adaugat la comanda

        OrderItem orderItem = orderItemRepository.findByOrderAndProduct(order, product.get());

        if (orderItem != null) {
            throw new Exception("Product already added to order");
        } else {
            // Create new order item if item isn't already added to order
            OrderItem newOrderItem = new OrderItem();
            newOrderItem.setOrder(order);
            newOrderItem.setProduct(product.get());
            newOrderItem.setQuantity(orderItemRequest.getQuantity());
            newOrderItem.setPrice(orderItemRequest.getPrice());

            orderItemRepository.save(newOrderItem);

            System.out.println(newOrderItem.getId());

            return newOrderItem;
        }
    }

    // Adaugare raspuns comanda

    public OrderStatus addOrderStatus(OrderStatusRequest orderStatusRequest) throws Exception {

        Order order = getOrderById(orderStatusRequest.getOrderId());

        if (order == null) {
            throw new Exception("Order with id " + orderStatusRequest.getOrderId() + " not found");
        }

        Optional<Farm> farm = farmRepository.findById(orderStatusRequest.getFarmId());

        if (!farm.isPresent()) {
            throw new Exception("Farmer with id " + orderStatusRequest.getFarmId() + " not found");
        }

        OrderStatus validatedOrderStatus = orderStatusRepository.findByOrderAndFarm(order, farm.get());

        if (validatedOrderStatus != null) {
            throw new Exception(
                    "Order status already added for order " + order.getId() + " and farm " + farm.get().getId());
        }

        OrderStatus orderStatus = new OrderStatus();

        orderStatus.setOrder(order);
        orderStatus.setFarm(farm.get());

        orderStatus.setOrderSubtotal(orderStatusRequest.getOrderSubtotal());

        orderStatus.setDeliveryFee(orderStatusRequest.getDeliveryFee());

        orderStatus.setStatus(0);

        orderStatusRepository.save(orderStatus);

        return orderStatus;

    }

    // Modificare status comanda

    public void modifyOrderStatus(ModifyOrderStatusRequest modifyOrderStatusRequest) throws Exception {
        OrderStatus orderStatus = findOrderStatusById(modifyOrderStatusRequest.getOrderStatusId());

        Optional<FarmUser> farmUser = farmUserRepository.findById(modifyOrderStatusRequest.getFarmUserId());

        if (!farmUser.isPresent()) {
            throw new Exception("Farm user not found");
        }

        // Setare status

        if(modifyOrderStatusRequest.getStatus()!=null && modifyOrderStatusRequest.getStatus().isPresent()){
            final Integer status = modifyOrderStatusRequest.getStatus().get();
      
            orderStatus.setStatus(status);

            // Realizare operatiuni in functie de status

            if (status == 3) {
                BigDecimal multiplier = new BigDecimal("0.03");
                orderStatus.setFarmFee(orderStatus.getDeliveryFee()
                        .add(orderStatus.getOrderSubtotal().subtract(orderStatus.getOrderSubtotal().multiply(multiplier))));
                orderStatus.setClientFee(new BigDecimal("0"));
            }
            if (status == -1) {
                orderStatus.setClientFee(orderStatus.getOrderSubtotal().add(orderStatus.getDeliveryFee()));
                orderStatus.setFarmFee(new BigDecimal("0"));
            }
        }

        // Setare estimare livrare

         if(modifyOrderStatusRequest.getDeliveryEstimate()!=null && modifyOrderStatusRequest.getDeliveryEstimate().isPresent()){
            orderStatus.setDeliveryEstimate(modifyOrderStatusRequest.getDeliveryEstimate().map(Object
                    ::toString).orElse(null));
         }

        orderStatus.setLastModFarmUser(farmUser.get());
        orderStatus.setLastModDt(LocalDateTime.now());

    }

    public OrderStatus findOrderStatusByOrderIdAndFarmId(Integer orderId, Integer farmId) throws Exception {
        Order order = getOrderById(orderId);

        if (order == null) {
            throw new Exception("Order with id " + orderId + " not found");
        }

        Optional<Farm> farm = farmRepository.findById(farmId);

        if (!farm.isPresent()) {
            throw new Exception("Farmer with id " + farmId + " not found");
        }

        OrderStatus validatedOrderStatus = orderStatusRepository.findByOrderAndFarm(order, farm.get());

        if (validatedOrderStatus == null) {
            throw new Exception(
                    "Order response not found for order " + order.getId() + " and farm " + farm.get().getId());
        }

        return validatedOrderStatus;
    }

    public OrderStatus findOrderStatusById(Integer orderStatusId) throws Exception {
        Optional<OrderStatus> orderStatus = orderStatusRepository.findById(orderStatusId);

        if (!orderStatus.isPresent()) {
            throw new Exception("Order status nout found");
        }

        return orderStatus.get();
    }

    public void addOrderStatusNotif(OrderStatusNotifRequest orderStatusNotifRequest) throws Exception {
        OrderStatus orderStatus = findOrderStatusById(orderStatusNotifRequest.getOrderStatusId());

        OrderStatusNotif orderStatusNotif = new OrderStatusNotif();

        orderStatusNotif.setOrderStatus(orderStatus);

        orderStatusNotif.setStatus(orderStatusNotifRequest.getStatus());

        orderStatusNotif.setSendingDt(LocalDateTime.now());

        orderStatusNotif.setEmail(orderStatusNotifRequest.getEmail());

        orderStatusNotif.setMessage(orderStatusNotifRequest.getMessage());

        orderStatusNotifRepository.save(orderStatusNotif);
    }

    public void addFundsDistrib(FundsDistribRequest fundsDistribRequest) throws Exception {

        Optional<OrderStatus> orderStatus = orderStatusRepository.findById(fundsDistribRequest.getOrderStatusId());

        if (!orderStatus.isPresent()) {
            throw new Exception("Order status not found");
        }

        Optional<Admin> admin = adminRepository.findById(fundsDistribRequest.getAdminId());

        if (!admin.isPresent()) {
            throw new Exception("Admin not found");
        }

        FundsDistrib validateFundsDistrib = fundsDistribRepository.findByOrderStatus(orderStatus.get());

        if (validateFundsDistrib != null) {
            throw new Exception("Funds already distributed");
        }

        FundsDistrib fundsDistrib = new FundsDistrib();

        fundsDistrib.setOrderStatus(orderStatus.get());

        fundsDistrib.setAdmin(admin.get());

        if (fundsDistribRequest.getClientSum().isPresent() && fundsDistribRequest.getClientSum() != null) {
            fundsDistrib.setClientSum(fundsDistribRequest.getClientSum().get());
        }

        if (fundsDistribRequest.getFarmSum().isPresent() && fundsDistribRequest.getFarmSum() != null) {
            fundsDistrib.setFarmSum(fundsDistribRequest.getFarmSum().get());
        }

        fundsDistrib.setDate(LocalDateTime.now());

        fundsDistribRepository.save(fundsDistrib);
    }

    public FundsDistrib findFundsDistrib(Integer orderStatusId) throws Exception {
        Optional<OrderStatus> orderStatus = orderStatusRepository.findById(orderStatusId);

        if (!orderStatus.isPresent()) {
            throw new Exception("Order status not found.");
        }

        FundsDistrib fundsDistrib = fundsDistribRepository.findByOrderStatus(orderStatus.get());

        if (fundsDistrib == null) {
            throw new Exception("Funds distrib not found.");
        }

        return fundsDistrib;
    }

    public void isFundsDistrib(Integer orderStatusId) throws Exception {
        Optional<OrderStatus> orderStatus = orderStatusRepository.findById(orderStatusId);

        if (!orderStatus.isPresent()) {
            throw new Exception("Order status not found.");
        }

        FundsDistrib fundsDistrib = fundsDistribRepository.findByOrderStatus(orderStatus.get());

        if (fundsDistrib == null) {
            throw new Exception("Funds distrib not found.");
        }
    }

}
