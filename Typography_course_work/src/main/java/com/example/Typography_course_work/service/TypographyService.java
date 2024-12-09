package com.example.Typography_course_work.service;

import com.example.Typography_course_work.dto.OrderDTO.create.ClientRequestDto;
import com.example.Typography_course_work.dto.OrderDTO.create.CreateOrderRequestDto;
import com.example.Typography_course_work.dto.OrderDTO.create.OrderItemRequestDto;
import com.example.Typography_course_work.dto.OrderDTO.get.AllOrderResponseDto;
import com.example.Typography_course_work.dto.OrderDTO.get.ClientResponseDto;
import com.example.Typography_course_work.dto.OrderDTO.get.OrderItemResponseDto;
import com.example.Typography_course_work.dto.OrderDTO.get.OrderResponseDto;
import com.example.Typography_course_work.dto.materialDto.get.MaterialForGetAllResponseDto;
import com.example.Typography_course_work.dto.materialDto.get.MaterialTwoFieldResponseDto;
import com.example.Typography_course_work.dto.productDTO.get.AllProductResponseDto;
import com.example.Typography_course_work.dto.productDTO.get.ProductResponseDto;
import com.example.Typography_course_work.dto.productDTO.post.ProductRequestDto;
import com.example.Typography_course_work.dto.provider.ProviderAllResponseDto;
import com.example.Typography_course_work.dto.provider.ProviderResponseDto;
import com.example.Typography_course_work.dto.reports.materialByOrder.MaterialByOrderChildrenResponseDto;
import com.example.Typography_course_work.dto.reports.materialByOrder.MaterialByOrderResponseDto;
import com.example.Typography_course_work.dto.reports.materials.MaterialReportAndTurnoverResponseDto;
import com.example.Typography_course_work.dto.reports.materials.MaterialReportResponseDto;
import com.example.Typography_course_work.dto.reports.money.MoveMoneyResponseDto;
import com.example.Typography_course_work.dto.reports.money.MoveMoneyTotalResponseDto;
import com.example.Typography_course_work.model.*;
import com.example.Typography_course_work.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TypographyService {
    //repository
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ClientRepository clientRepository;
    private final MaterialRepository materialRepository;
    private final ProviderRepository providerRepository;
    private final MaterialTurnoverRepository materialTurnoverRepository;

    public void create(CreateOrderRequestDto createOrderRequestDto) {
        try {
            convertOrderDtoToOrderModel(createOrderRequestDto);
        } catch (Exception e) {
            log.info("ошибка при сохранении заказа");
        }
    }

    private void convertOrderDtoToOrderModel(CreateOrderRequestDto createOrderRequestDto) {
        Order orderModel = new Order();
        try {
            orderModel.setId(orderRepository.count()+1);
        } catch (Exception e) {
            log.warn("error save in order set id");
        }
        Long clientId = convertClientDtoToClientModel(createOrderRequestDto.getClient()).getId();
        List<Integer> orderItemListIds = convertOrderItemsDtoToOrderItemModel(
                createOrderRequestDto.getOrderItems(),
                orderModel.getId());

        orderModel.setClientId(clientId);
        orderModel.setOrderItems(orderItemListIds);

        try {
            orderRepository.save(orderModel);
        } catch (Exception e) {
            log.warn("error in convertOrderDtoToOrderModel repository save");
        }
    }

    private List<Integer> convertOrderItemsDtoToOrderItemModel(List<OrderItemRequestDto> orderItems, Long orderId) {
        List<Integer> result = new ArrayList<>();
        for (OrderItemRequestDto orderItemRequestDto : orderItems) {
            result.add(convertOrderItemDtoToOrderItemModel(orderItemRequestDto, orderId).getId().intValue());
        }
        return result;
    }

    /**
     *
     * method for work with order item request dto
     * @param dto
     * @param orderId
     * @return
     */
    private OrderItem convertOrderItemDtoToOrderItemModel(OrderItemRequestDto dto, Long orderId) {
        OrderItem orderItem = new OrderItem();
        Integer orderItemCount = dto.getCount();   // count for multiply for delete from warehouse
        orderItem.setCount(orderItemCount);
        Long productId = null;
        List<Integer> materials = null;
        try {
            Product product = productRepository.findById(Long.parseLong(dto.getProduct())).get();
            productId = product.getId();
            materials = product.getMaterials();
        } catch (NumberFormatException e) {
            log.warn("error in convertOrderItemDtoToOrderItemModel repository findById");
        }
        if (materials != null) {
            for (Integer materialId : materials) {
                MaterialsTurnover materialsTurnover = new MaterialsTurnover();
                materialsTurnover.setCount(orderItemCount);
                materialsTurnover.setMaterialId(materialId.longValue());
                try {
                    materialTurnoverRepository.save(materialsTurnover);
                } catch (Exception e) {
                    log.warn("error in save material turnover");
                }
                materialSpentCountById(materialId, orderItemCount);
                log.info("был потрачен материал с id{}\n в кол-ве {}", materialId, orderItemCount);
            }
        }
        orderItem.setProductId(productId);
        orderItem.setOrderId(orderId);
        OrderItem save = null;
        try {
            save = orderItemRepository.save(orderItem);
        } catch (Exception e) {
            log.warn("error in convertOrderItemDtoToOrderItemModel repository save");
        }
        return save;
    }

    private void materialSpentCountById(Integer materialId, Integer materialCount) {
        Material material = null;
        try {
            material = materialRepository.findById(materialId.longValue()).get();
        } catch (Exception e) {
            log.warn("error in find by id in material");
        }
        if (material != null) {
            material.setCount(material.getCount() - materialCount);  // тратим материалы
            material.setCountOfSpent(material.getCountOfSpent() + materialCount);  // add spend count
        }

    }

    private Client convertClientDtoToClientModel(ClientRequestDto dto) {
        clientRepository.findByEmail(dto.getEmail())
        Client client = new Client();
        client.setName(dto.getName());
        client.setSecondName(dto.getSecondName());
        client.setCard(dto.getCard());
        client.setPhone(dto.getPhone());
        client.setEmail(dto.getEmail());
        Client save = null;
        try {
            save = clientRepository.save(client);
        } catch (Exception e) {
            log.warn("error in convertClientDtoToClientModel repository save");
        }
        return save;
        //        clientRepository.save(client);
//        return new Client(
//                0L,
//                clientRequestDto.getName(),
//                clientRequestDto.getSecondName(),
//                clientRequestDto.getEmail(),
//                clientRequestDto.getPhone(),
//                clientRequestDto.getCard()
//        );
    }

    public AllProductResponseDto getAllProducts() {
        List<Product> allProduct = null;
        try {
            allProduct = productRepository.findAll();
            Collections.reverse(allProduct);
        } catch (Exception e) {
            log.warn("error in getAllProducts because repository find all");
        }
        List<ProductResponseDto> children = convertProductListToProductListDto(allProduct);
        return new AllProductResponseDto(children);
    }

    private List<ProductResponseDto> convertProductListToProductListDto(List<Product> allProduct) {
        List<ProductResponseDto> children = new ArrayList<>();
        for (Product product : allProduct) {
            children.add(
                    convertProductModelToProductResponseDto(product)
            );
        }
        return children;
    }

    private ProductResponseDto convertProductModelToProductResponseDto(Product product) {
        String materials = null;
        try {
            materials = convertMaterialsIdsToStringOfMaterials(product.getMaterials());
        } catch (Exception e) {
            log.warn("error in convertMaterialsIdsToStringOfMaterials");
        }
        return new ProductResponseDto(
                product.getId().toString(),
                product.getTitle(),
                product.getPrice(),
                materials
        );
    }

    private String convertMaterialsIdsToStringOfMaterials(List<Integer> materials) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer materialId : materials) {
            try {
                String title = materialRepository.findById(materialId.longValue()).get().getTitle();
                stringBuilder.append(title).append(", ");
            } catch (Exception e) {
                log.warn("error in materialRepository.findById");
            }
        }
        if (stringBuilder.length() > 2) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        return stringBuilder.toString();
    }

    public AllOrderResponseDto getAllOrders() {
//        int totalPrice = 0;
//        List<OrderResponseDto> children = new ArrayList<>();
        List<Order> orderList = null;
        try {
            orderList = orderRepository.findAll();
        } catch (Exception e) {
            log.warn("error in getAllOrders repository find all");
        }
        Collections.reverse(orderList);

        AllOrderResponseDto allOrderResponseDto = new AllOrderResponseDto();
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        for (Order order : orderList) {
            int totalPrice = 0;
            Client client = clientRepository.findById(order.getClientId()).get();
//            log.info("client is {}", client.toString());
            List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<>();
            List<Integer> orderItemsIds = order.getOrderItems();
            for (Integer orderItemId : orderItemsIds) {
//                log.info("orderItemId is {}", orderItemId);
                OrderItem orderItem = orderItemRepository.findById(orderItemId.longValue()).get();
//                log.info("orderItem is {}", orderItem);
                Product product = productRepository.findById(orderItem.getProductId()).get();
//                log.info("product is {}", product);
                Integer count = orderItem.getCount();
                orderItemResponseDtoList.add(
                        new OrderItemResponseDto(product.getTitle(),
                                count)
                );
                totalPrice += product.getPrice() * count;
            }
            orderResponseDtoList.add(new OrderResponseDto(
                    order.getId(),
                    convertClientModelToClientDto(client),
                    orderItemResponseDtoList,
                    totalPrice

            ));


        }
        allOrderResponseDto.setChildren(orderResponseDtoList);


//        for (Order order : orderList) {
//            List<Long> orderItemsIds = order.getOrderItems();
//            List<OrderItemResponseDto> orderItemResponseDtos = new ArrayList<>();
//            for (Long orderItemId : orderItemsIds) {
//                orderItemResponseDtos.add(
//                        orderRepository.findById(orderItemId)
//
//                        new OrderItemResponseDto(
//                        orderItemId.getProduct().getTitle(),
//                        orderItemId.getCount()
//                ));
//                totalPrice += orderItemId.getProduct().getPrice() * orderItemId.getCount();
//
//            }
//            Client client = order.getClient();
//            children.add(new OrderResponseDto(
//                    new ClientResponseDto(client.getName(),
//                            client.getSecondName(),
//                            client.getEmail(),
//                            client.getPhone(),
//                            client.getCard()),
//                    orderItemResponseDtos,
//                    totalPrice
//            ));
//        }
//        return new AllOrderResponseDto(children);
        return allOrderResponseDto;
    }


    private ClientResponseDto convertClientModelToClientDto(Client client) {
        return new ClientResponseDto(
                client.getName(),
                client.getSecondName(),
                client.getEmail(),
                client.getPhone(),
                client.getCard()
        );
    }

    public void saveProduct(ProductRequestDto productDto) {
        try {
            productRepository.save(convertProductDtoToProductModel(productDto));
        } catch (Exception e) {
            log.warn("error in saveProduct because repository save");
        }
    }

    private Product convertProductDtoToProductModel(ProductRequestDto productDto) {

        Product product;
        try {
            product = productRepository.findById(productDto.getId()).get();
        } catch (Exception e) {
            product = new Product();
        }
        product.setPrice(productDto.getPrice_with_materials());
        product.setTitle(productDto.getTitle());
        List<Integer> materialsId = getMaterialsIdsByMaterialsNameList(productDto.getMaterials());
        product.setMaterials(materialsId);
        log.info("method convertProductDtoToProductModel product = {}", product.toString());
        return product;
    }

    private List<Integer> getMaterialsIdsByMaterialsNameList(List<String> materials) {
        List<Integer> result = new ArrayList<>();

        for (String materialName : materials) {

            try {
                result.add(materialRepository.findByTitle(materialName).getId().intValue());
            } catch (Exception e) {
                log.warn("error in getMaterialsIdsByMaterialsNameList because findByTitle");
            }

        }
        return result;
    }

    public ProductResponseDto getProductByProductId(Long productId) {
        Product productModel;
        try {
             productModel = productRepository.findById(productId).get();
        } catch (Exception e) {
            log.warn("error in getProductByProductId repository findById id = {}", productId);
            return null;
        }

        return convertProductModelToProductResponseDto(productModel);
    }

    public MaterialForGetAllResponseDto getAllMaterialsDtos() {

        List<Material> materialModelList = null;
        try {
            materialModelList = materialRepository.findAll();
        } catch (Exception e) {
            materialModelList = new ArrayList<>();
        }
        List<MaterialTwoFieldResponseDto> children = convertMaterialModelListToMaterialDtoList(materialModelList);
        return new MaterialForGetAllResponseDto(children);
    }

    private List<MaterialTwoFieldResponseDto> convertMaterialModelListToMaterialDtoList(List<Material> materialModelList) {
        List<MaterialTwoFieldResponseDto> result = new ArrayList<>();
        for (Material material : materialModelList) {
            result.add(convertMaterialToMaterialDto(material));
        }
        return result;
    }

    private MaterialTwoFieldResponseDto convertMaterialToMaterialDto(Material material) {
        return new MaterialTwoFieldResponseDto(
                material.getTitle(),
                material.getPrice()
        );
    }

    public ProviderAllResponseDto getAllProviders() {
        List<Provider> allProviders = null;
        try {
            allProviders = providerRepository.findAll();
        } catch (Exception e) {
            log.warn("error in find all provider");
        }
        List<ProviderResponseDto> children = convertAllProvidersToAllProviderDto(allProviders);
        return new ProviderAllResponseDto(children);
    }

    private List<ProviderResponseDto> convertAllProvidersToAllProviderDto(List<Provider> allProviders) {
        List<ProviderResponseDto> listDtos = new ArrayList<>();
        for (Provider provider : allProviders) {
            listDtos.add(convertProviderDtoProviderDto(provider));
        }
        return listDtos;
    }

    private ProviderResponseDto convertProviderDtoProviderDto(Provider provider) {
        Material material = null;
        try {
            material = materialRepository.findById(provider.getMaterialId()).get();
        } catch (Exception e) {
            log.warn("error in findById material");
        }
        String materialName;
        if (material == null) {
            materialName = " ";
        } else {
            materialName = material.getTitle();
        }
        return new ProviderResponseDto(
                provider.getId(),
                provider.getName(),
                materialName,
                provider.getContactInfo()
        );
    }

    public MaterialReportAndTurnoverResponseDto materialReport() {
        List<MaterialReportResponseDto> children = getMaterialsReportsList();
        return new MaterialReportAndTurnoverResponseDto(children);
    }

    private List<MaterialReportResponseDto> getMaterialsReportsList() {
        List<MaterialReportResponseDto> result = new ArrayList<>();
        List<Material> allMaterials = null;
        try {
            allMaterials = materialRepository.findAll();
        } catch (Exception e) {
            log.warn("error in find all");
        }
        if (allMaterials != null) {
            for (Material material : allMaterials) {
                result.add(createMaterialReportByMaterial(material));
            }
        }
        return result;
    }

    private MaterialReportResponseDto createMaterialReportByMaterial(Material material) {
        List<MaterialsTurnover> materialsTurnoverList = null;
        try {
            materialsTurnoverList = materialTurnoverRepository.findAllByMaterialId(material.getId());
        } catch (Exception e) {
            log.warn("error in find by material id");
        }
        Integer totalCount = 0;
        if (materialsTurnoverList != null) {
            for (MaterialsTurnover materialsTurnover : materialsTurnoverList) {
                totalCount += materialsTurnover.getCount();
            }
        }
        return new MaterialReportResponseDto(
                material.getTitle(),
                material.getCount(),
                material.getCountOfSpent(),
                totalCount,
                material.getPrice() * totalCount
        );

    }

    public MoveMoneyTotalResponseDto moneyReport() {
        List<MoveMoneyResponseDto> children = createMoveMoneyDtoList();
        return new MoveMoneyTotalResponseDto(children);

    }

    private List<MoveMoneyResponseDto> createMoveMoneyDtoList() {
        List<MoveMoneyResponseDto> result = new ArrayList<>();

        for (OrderResponseDto orderResponseDto : getAllOrders().getChildren()) {
            StringBuilder things = createOrderItemsThings(orderResponseDto.getOrderItems());

            result.add(new MoveMoneyResponseDto(
                    things.toString(),
                    orderResponseDto.getClient().getName(),
                    orderResponseDto.getTotalPrice(),
                    "продажа товаров"
            ));
        }

        for (MaterialsTurnover materialsTurnover : materialTurnoverRepository.findAll()) {
            Long materialId = materialsTurnover.getMaterialId();
            String title;
            Integer materialPrice = null;
            try {
                Material material = materialRepository.findById(materialId).get();
                materialPrice = material.getPrice() * materialsTurnover.getCount();
                title = material.getTitle();
            } catch (Exception e) {
                log.warn("find by id materials");
                continue;
            }

            String providerName = findProviderNameByMaterialId(materialId);

            result.add(new MoveMoneyResponseDto(
                    title,
                    providerName,
                    materialPrice,
                    "закупка материала"
            ));
        }
        Collections.shuffle(result);
        return result;
    }

    private String findProviderNameByMaterialId(Long materialId) {
        Provider provider = null;
        try {
            provider = providerRepository.findByMaterialId(materialId);
            return provider.getName();
        } catch (Exception e) {
            log.warn("error in find by material provider");
        }
        return null;

    }

    private StringBuilder createOrderItemsThings(List<OrderItemResponseDto> orderItems) {
        StringBuilder result = new StringBuilder();
        for (OrderItemResponseDto orderItem : orderItems) {
            result.append(orderItem.getProductName()).append(", ");
        }
        if (result.length() > 2) {
            result.delete(result.length() - 2, result.length());
        }
        return result;
    }

    public MaterialByOrderResponseDto materialByOrder() {
        List<MaterialByOrderChildrenResponseDto> children = createMaterialByOrderDtoList(); 
        return new MaterialByOrderResponseDto(children);
    }

    private List<MaterialByOrderChildrenResponseDto> createMaterialByOrderDtoList() {
        List<MaterialByOrderChildrenResponseDto> result = new ArrayList<>();
        List<Order> orderList = orderRepository.findAll();

        for (Order order : orderList) {
            result.add(new MaterialByOrderChildrenResponseDto(
                    order.getId(),
                    findMaterialsListByOrder(order)
            ));
        }
        return result;
    }

    private List<String> findMaterialsListByOrder(Order order) {
        Set<String> setMaterials = new HashSet<>();
        List<Integer> orderItems = order.getOrderItems();
        for (Integer orderItemId : orderItems) {
            Product product = null;
            try {
                OrderItem orderItem = orderItemRepository.findById(orderItemId.longValue()).get();
                product = productRepository.findById(orderItem.getProductId()).get();
            } catch (Exception e) {
                log.warn("error in find by id or find by id");
                continue;
            }
            List<Integer> materials = product.getMaterials();
            if (findMaterialsListByMaterialsIdList(materials) != null) {
                setMaterials.addAll(findMaterialsListByMaterialsIdList(materials));
            }
        }
        return setMaterials.stream().toList();
    }

    private Set<String> findMaterialsListByMaterialsIdList(List<Integer> materials) {
        Set<String> result = new HashSet<>();
        for (Integer materialIds : materials) {
            Material material = null;
            try {
                material = materialRepository.findById(materialIds.longValue()).get();
            } catch (Exception e) {
                log.warn("error in find by id material");
                continue;
            }
            result.add(material.getTitle());
        }
        return result;
    }
}
