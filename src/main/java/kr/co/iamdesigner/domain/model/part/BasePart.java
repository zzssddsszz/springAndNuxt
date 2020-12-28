package kr.co.iamdesigner.domain.model.part;

import kr.co.iamdesigner.domain.model.part.PlatingColor;
import kr.co.iamdesigner.domain.model.part.SuppliedPart;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BasePart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "part")
    private List<SuppliedPart> suppliedPartList = new ArrayList<>();

    @Column
    private String name;

    @Column
    private int buyPrice;

    @Column
    private int sellPrice;

    @Column
    private int stock;

    @Column
    @Enumerated(EnumType.STRING)
    private PlatingColor color;

    @Column
    @Enumerated(EnumType.STRING)
    private Material material;

    @Builder
    public BasePart(String name, int buyPrice, int sellPrice, int stock, PartType partType, PlatingColor color,Material material) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.stock = stock;
        this.color = color;
        this.material = material;
    }
}
