package com.br.faeterj.paracambi.sarcaspd

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.br.faeterj.paracambi.sarcaspd.data.model.OptionsRule
import com.br.faeterj.paracambi.sarcaspd.data.model.Rule
import com.br.faeterj.paracambi.sarcaspd.data.repository.FieldsRepository
import com.br.faeterj.paracambi.sarcaspd.util.ResultUtil
import com.google.common.truth.Truth
import dagger.hilt.android.qualifiers.ApplicationContext
import io.mockk.MockKAnnotations
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule as RuleJUnit
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class CalculateTest : TestCase() {

    @get:RuleJUnit
    val instantTestRule = InstantTaskExecutorRule()

    @ApplicationContext
    @Mock
    private var context = mockk<Application>(relaxed = true)

    @Mock
    private val rules: List<Rule> = listOf(
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 1, idOption = 1),
                OptionsRule(idQuestion = 2, idOption = 1)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 1, idOption = 1),
                OptionsRule(idQuestion = 2, idOption = 2)
            ), value = 3
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 1, idOption = 1),
                OptionsRule(idQuestion = 2, idOption = 3)
            ), value = 1
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 1, idOption = 2),
                OptionsRule(idQuestion = 2, idOption = 1)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 1, idOption = 2),
                OptionsRule(idQuestion = 2, idOption = 2)
            ), value = 4
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 1, idOption = 2),
                OptionsRule(idQuestion = 2, idOption = 3)
            ), value = 3
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 1, idOption = 3),
                OptionsRule(idQuestion = 2, idOption = 1)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 1, idOption = 3),
                OptionsRule(idQuestion = 2, idOption = 2)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 1, idOption = 3),
                OptionsRule(idQuestion = 2, idOption = 3)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 1, idOption = 1),
                OptionsRule(idQuestion = 3, idOption = 1)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 1, idOption = 1),
                OptionsRule(idQuestion = 3, idOption = 2)
            ), value = 3
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 1, idOption = 1),
                OptionsRule(idQuestion = 3, idOption = 3)
            ), value = 1
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 1, idOption = 2),
                OptionsRule(idQuestion = 3, idOption = 1)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 1, idOption = 2),
                OptionsRule(idQuestion = 3, idOption = 2)
            ), value = 4
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 1, idOption = 2),
                OptionsRule(idQuestion = 3, idOption = 3)
            ), value = 3
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 1, idOption = 3),
                OptionsRule(idQuestion = 3, idOption = 1)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 1, idOption = 3),
                OptionsRule(idQuestion = 3, idOption = 2)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 1, idOption = 3),
                OptionsRule(idQuestion = 3, idOption = 3)
            ), value = 4
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 4, idOption = 1),
                OptionsRule(idQuestion = 5, idOption = 1)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 4, idOption = 1),
                OptionsRule(idQuestion = 5, idOption = 2)
            ), value = 4
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 4, idOption = 1),
                OptionsRule(idQuestion = 5, idOption = 3)
            ), value = 3
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 4, idOption = 2),
                OptionsRule(idQuestion = 6, idOption = 1)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 4, idOption = 2),
                OptionsRule(idQuestion = 6, idOption = 2)
            ), value = 4
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 4, idOption = 2),
                OptionsRule(idQuestion = 6, idOption = 3)
            ), value = 3
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 4, idOption = 3),
                OptionsRule(idQuestion = 7, idOption = 1)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 4, idOption = 3),
                OptionsRule(idQuestion = 7, idOption = 2)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 4, idOption = 3),
                OptionsRule(idQuestion = 7, idOption = 3)
            ), value = 4
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 4, idOption = 4),
                OptionsRule(idQuestion = 8, idOption = 1)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 4, idOption = 4),
                OptionsRule(idQuestion = 8, idOption = 2)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 4, idOption = 4),
                OptionsRule(idQuestion = 8, idOption = 3)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 9, idOption = 1),
                OptionsRule(idQuestion = 10, idOption = 1)
            ), value = 2
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 9, idOption = 1),
                OptionsRule(idQuestion = 10, idOption = 2)
            ), value = 3
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 9, idOption = 1),
                OptionsRule(idQuestion = 10, idOption = 3)
            ), value = 1
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 9, idOption = 2),
                OptionsRule(idQuestion = 10, idOption = 1)
            ), value = 3
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 9, idOption = 2),
                OptionsRule(idQuestion = 10, idOption = 2)
            ), value = 4
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 9, idOption = 2),
                OptionsRule(idQuestion = 10, idOption = 3)
            ), value = 2
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 9, idOption = 3),
                OptionsRule(idQuestion = 10, idOption = 1)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 9, idOption = 3),
                OptionsRule(idQuestion = 10, idOption = 2)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 9, idOption = 3),
                OptionsRule(idQuestion = 10, idOption = 3)
            ), value = 4
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 12, idOption = 1),
                OptionsRule(idQuestion = 11, idOption = 1)
            ), value = 1
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 12, idOption = 1),
                OptionsRule(idQuestion = 11, idOption = 2)
            ), value = 3
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 12, idOption = 1),
                OptionsRule(idQuestion = 11, idOption = 3)
            ), value = 4
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 12, idOption = 2),
                OptionsRule(idQuestion = 11, idOption = 1)
            ), value = 2
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 12, idOption = 2),
                OptionsRule(idQuestion = 11, idOption = 2)
            ), value = 4
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 12, idOption = 2),
                OptionsRule(idQuestion = 11, idOption = 3)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 12, idOption = 3),
                OptionsRule(idQuestion = 11, idOption = 1)
            ), value = 3
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 12, idOption = 3),
                OptionsRule(idQuestion = 11, idOption = 2)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 12, idOption = 3),
                OptionsRule(idQuestion = 11, idOption = 3)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 13, idOption = 1),
                OptionsRule(idQuestion = 12, idOption = 1)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 13, idOption = 1),
                OptionsRule(idQuestion = 12, idOption = 2)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 13, idOption = 1),
                OptionsRule(idQuestion = 12, idOption = 3)
            ), value = 5
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 13, idOption = 2),
                OptionsRule(idQuestion = 12, idOption = 1)
            ), value = 2
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 13, idOption = 2),
                OptionsRule(idQuestion = 12, idOption = 2)
            ), value = 3
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 13, idOption = 2),
                OptionsRule(idQuestion = 12, idOption = 3)
            ), value = 4
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 13, idOption = 3),
                OptionsRule(idQuestion = 12, idOption = 1)
            ), value = 1
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 13, idOption = 3),
                OptionsRule(idQuestion = 12, idOption = 2)
            ), value = 2
        ),
        Rule(
            options = listOf(
                OptionsRule(idQuestion = 13, idOption = 3),
                OptionsRule(idQuestion = 12, idOption = 3)
            ), value = 3
        ),
    )
    private lateinit var repository: FieldsRepository

    @Before
    override fun setUp() {
        super.setUp()
        MockKAnnotations.init()
        repository = FieldsRepository(context)
    }

    @Test
    fun test_calculate_field_case_1() = runBlocking<Unit>{
        val optionsSelected: List<OptionsRule> = listOf(
            OptionsRule(idQuestion = 1, idOption = 1),
            OptionsRule(idQuestion = 2, idOption = 1),
            OptionsRule(idQuestion = 3, idOption = 1),
            OptionsRule(idQuestion = 4, idOption = 1),
            OptionsRule(idQuestion = 5, idOption = 1),
            OptionsRule(idQuestion = 9, idOption = 1),
            OptionsRule(idQuestion = 10, idOption = 1),
            OptionsRule(idQuestion = 11, idOption = 1),
            OptionsRule(idQuestion = 12, idOption = 1),
            OptionsRule(idQuestion = 13, idOption = 1),
        )

        val result = repository.calculateFields(rules, optionsSelected)
        val actualResult = repository.getFinalResult(result)?.copy()

        val expectedResult = ResultUtil.finalResults[ResultUtil.ATENCAO]!!.copy()
        expectedResult.total = 23

        Truth.assertThat(expectedResult).isEqualTo(actualResult)
    }

    @Test
    fun test_calculate_field_case_2() = runBlocking<Unit>{
        val optionsSelected: List<OptionsRule> = listOf(
            OptionsRule(idQuestion = 1, idOption = 1),
            OptionsRule(idQuestion = 2, idOption = 3),
            OptionsRule(idQuestion = 3, idOption = 2),
            OptionsRule(idQuestion = 4, idOption = 1),
            OptionsRule(idQuestion = 5, idOption = 3),
            OptionsRule(idQuestion = 9, idOption = 1),
            OptionsRule(idQuestion = 10, idOption = 2),
            OptionsRule(idQuestion = 11, idOption = 3),
            OptionsRule(idQuestion = 12, idOption = 1),
            OptionsRule(idQuestion = 13, idOption = 2),
        )

        val result = repository.calculateFields(rules, optionsSelected)
        val actualResult = repository.getFinalResult(result)?.copy()

        val expectedResult = ResultUtil.finalResults[ResultUtil.ATENCAO]!!.copy()
        expectedResult.total = 16

        Truth.assertThat(expectedResult).isEqualTo(actualResult)
    }

    @Test
    fun test_calculate_field_case_3() = runBlocking<Unit>{
        val optionsSelected: List<OptionsRule> = listOf(
            OptionsRule(idQuestion = 1, idOption = 3),
            OptionsRule(idQuestion = 2, idOption = 2),
            OptionsRule(idQuestion = 3, idOption = 1),
            OptionsRule(idQuestion = 4, idOption = 1),
            OptionsRule(idQuestion = 4, idOption = 2),
            OptionsRule(idQuestion = 4, idOption = 3),
            OptionsRule(idQuestion = 4, idOption = 4),
            OptionsRule(idQuestion = 5, idOption = 3),
            OptionsRule(idQuestion = 6, idOption = 2),
            OptionsRule(idQuestion = 7, idOption = 1),
            OptionsRule(idQuestion = 8, idOption = 3),
            OptionsRule(idQuestion = 9, idOption = 1),
            OptionsRule(idQuestion = 10, idOption = 1),
            OptionsRule(idQuestion = 11, idOption = 3),
            OptionsRule(idQuestion = 12, idOption = 1),
            OptionsRule(idQuestion = 13, idOption = 2),
        )

        val result = repository.calculateFields(rules, optionsSelected)
        val actualResult = repository.getFinalResult(result)?.copy()

        val expectedResult = ResultUtil.finalResults[ResultUtil.ALTO]!!.copy()
        expectedResult.total = 35

        Truth.assertThat(expectedResult).isEqualTo(actualResult)
    }

}