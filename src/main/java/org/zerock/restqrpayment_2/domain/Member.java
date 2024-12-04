package org.zerock.restqrpayment_2.domain;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.*;
import org.zerock.restqrpayment_2.dto.MemberDTO;

import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "roleSet")
public class Member extends BaseEntity{

    @Id
    private String mid;

    private String mpw;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void changePassword(String mpw) {
        this.mpw = mpw;
    }

    public void addRole(MemberRole role) {
        this.roleSet.add(role);
    }

    public void clearRoles() {
        this.roleSet.clear();
    }

    // MemberEntity를 MemberDTO로 변환
    public MemberDTO toDTO() {
        return MemberDTO.builder()
                .mid(this.mid)
                .mpw(this.mpw)
                .roles(this.roleSet)
                .build();
    }
}
