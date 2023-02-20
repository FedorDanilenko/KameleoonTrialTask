package com.example.KameleoonTrialTask.unity.service;

import com.example.KameleoonTrialTask.dto.graph.ListQuoteDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteDetailDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteInDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteOutDto;
import com.example.KameleoonTrialTask.entity.QuoteEntity;
import com.example.KameleoonTrialTask.entity.SortType;
import com.example.KameleoonTrialTask.entity.UserEntity;
import com.example.KameleoonTrialTask.exception.AlreadyExistEx;
import com.example.KameleoonTrialTask.exception.NotFoundEx;
import com.example.KameleoonTrialTask.mapper.QuoteMapper;
import com.example.KameleoonTrialTask.repository.QuoteRepo;
import com.example.KameleoonTrialTask.repository.UserRepo;
import com.example.KameleoonTrialTask.repository.VoteRepo;
import com.example.KameleoonTrialTask.service.QuoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuoteServiceTest {

    private static final Long ID_1 = 1L;
    private static final Long ID_2 = 2L;
    private static final Long USER_ID = 3L;
    private final QuoteInDto quoteInDto = QuoteInDto.builder()
            .text("text1")
            .build();
    private final QuoteOutDto quoteOutDto = QuoteOutDto.builder()
            .id(ID_1)
            .text("text1")
            .build();
    private final QuoteEntity quote1 = QuoteEntity.builder()
            .id(ID_1)
            .text("text1")
            .score(5L)
            .build();
    private final QuoteEntity quote2 = QuoteEntity.builder()
            .id(ID_2)
            .text("text2")
            .score(2L)
            .build();
    private final QuoteOutDto quoteUp = QuoteOutDto.builder()
            .id(ID_2)
            .text("New text")
            .build();
    private final QuoteDetailDto quoteDetail2 = QuoteDetailDto.builder()
            .id(ID_2)
            .text("text2")
            .build();
    private final UserEntity user = UserEntity.builder()
            .id(USER_ID)
            .name("login2")
            .email("email@email.com")
            .password("pass")
            .build();
    private final ListQuoteDto listTop = ListQuoteDto.builder()
            .quotes(List.of(quote1,quote2))
            .build();

    @InjectMocks
    private QuoteService quoteService;
    @Mock
    private QuoteRepo quoteRepo;
    @Mock
    private QuoteMapper quoteMapper;
    @Mock
    private UserRepo userRepo;
    @Mock
    private VoteRepo voteRepo;
    @Test
    void addQuote() throws NotFoundEx, AlreadyExistEx {
        Mockito.when(quoteRepo.save(any())).thenReturn(quote1);
        Mockito.when(userRepo.findById(any())).thenReturn(Optional.ofNullable(user));
        Mockito.when(quoteMapper.toQuote(quoteInDto)).thenReturn(quote1);
        Mockito.when(quoteMapper.toDto(quote1)).thenReturn(quoteOutDto);

        QuoteOutDto quoteResult = quoteService.addQuote(quoteInDto);

        assertEquals(ID_1, quoteResult.getId());
        assertEquals("text1", quoteResult.getText());
        verify(quoteRepo, times(1)).save(quote1);
    }

    @Test
    void getQuote() throws NotFoundEx {
        Mockito.when(quoteRepo.findById(anyLong())).thenReturn(Optional.of(quote1));
        Mockito.when(quoteMapper.toDto(quote1)).thenReturn(quoteOutDto);

        QuoteOutDto quoteResunlt2 = quoteService.getQuote(ID_1);

        assertEquals(ID_1, quoteResunlt2.getId());
        assertEquals("text1", quoteResunlt2.getText());
        verify(quoteRepo, times(1)).findById(ID_1);
    }

    @Test
    void getTop() {
        Mockito.when(quoteRepo.findAll()).thenReturn(List.of(quote1,quote2));
        Mockito.when(quoteMapper.toListTop(List.of(quote1,quote2)))
                .thenReturn(listTop);

        ListQuoteDto listTopResult = quoteService.getTop(SortType.TOP);

        int size = 2;
        assertEquals(size, listTopResult.getQuotes().size());
        verify(quoteRepo, times(1)).findAll();
    }

    @Test
    void getRandomQuote() {
        Mockito.when(quoteRepo.findAll()).thenReturn(List.of(quote1));
        Mockito.when(quoteMapper.toDto(quote1)).thenReturn(quoteOutDto);

        QuoteOutDto quoteResult3 = quoteService.getRandomQuote();

        assertNotNull(quoteResult3.getId());
        verify(quoteRepo, times(1)).findAll();

    }

    @Test
    void getQuoteDetail()  throws NotFoundEx {
        Mockito.when(quoteRepo.findById(anyLong())).thenReturn(Optional.of(quote2));
        Mockito.when(quoteMapper.toDetail(quote2)).thenReturn(quoteDetail2);

        QuoteDetailDto quoteDetailResult = quoteService.getQuoteDetail(ID_2);

        assertEquals(ID_2, quoteDetailResult.getId());
        assertEquals("text2", quoteDetailResult.getText());
        verify(quoteRepo, times(1)).findById(ID_2);
    }

    @Test
    void updateQuote() throws NotFoundEx {
        Mockito.when(quoteRepo.findById(anyLong())).thenReturn(Optional.of(quote2));
        Mockito.when(quoteRepo.saveAndFlush(quote2)).thenReturn(quote2);
        Mockito.when(quoteMapper.toDto(quote2)).thenReturn(quoteUp);

        QuoteOutDto quoteResult4 = quoteService.updateQuote(ID_2, quoteInDto);

        assertEquals(ID_2, quoteResult4.getId());
        assertEquals("New text", quoteResult4.getText());
        verify(quoteRepo, times(1)).findById(ID_2);
        verify(quoteRepo, times(1)).saveAndFlush(quote2);
    }

    @Test
    void deleteQuote() throws NotFoundEx {
        Mockito.when(quoteRepo.findById(anyLong())).thenReturn(Optional.of(quote1));

        quoteService.deleteQuote(ID_1);

        verify(quoteRepo, times(1)).findById(ID_1);
        verify(voteRepo, times(1)).deleteByQuote(quote1);

    }
}