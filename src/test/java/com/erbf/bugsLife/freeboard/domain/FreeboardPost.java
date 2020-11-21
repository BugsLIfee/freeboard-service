package com.erbf.bugsLife.freeboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class FreeboardPost {

        @Id
        @GeneratedValue( strategy = GenerationType.IDENTITY)
        private Long id;

        private String cate;
        private String title;

        @Column(length= 10000)
        private String content;
        private String registerDate;
        private String updateDate;
        private Boolean isHide;
        @Column(nullable = false)
        private String pwd;

        @Column(columnDefinition = "integer default 0")
        private int viewCnt;
        private int reportCnt;
        private int likes;

//        @Builder.Default
//        @OneToMany(mappedBy = "post" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//        private List<FreeboardComment> comments = new ArrayList<FreeboardComment>();


        public void addLikes() { this.likes = this.likes +1;
        }

        public void disLikes(){
                this.likes = this.likes-1;
        }

        public void addViewCnt() {
                this.viewCnt = this.viewCnt+1;
        }
}
