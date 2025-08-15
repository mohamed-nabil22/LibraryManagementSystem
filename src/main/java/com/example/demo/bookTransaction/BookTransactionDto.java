package com.example.demo.bookTransaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data //this have all(Getters,setters ,constructors)
public class BookTransactionDto {
    Long memberId;
    Long bookId;
}
