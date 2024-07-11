package edu.odu.cs.cs330;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static edu.odu.cs.cs330.items.Inventory.DEFAULT_SIZE;

/**
 * 1 - Does this piece of code perform the operations
 *     it was designed to perform?
 *
 * 2 - Does this piece of code do something it was not
 *     designed to perform?
 *
 * 1 Test per mutator
 *
 * This is technically an Integration Test.
 */
@SuppressWarnings({
    "PMD.AtLeastOneConstructor",
    "PMD.AvoidDuplicateLiterals",
    "PMD.BeanMembersShouldSerialize",
    "PMD.JUnitAssertionsShouldIncludeMessage",
    "PMD.JUnitTestContainsTooManyAsserts",
    "PMD.LocalVariableCouldBeFinal",
    "PMD.MethodArgumentCouldBeFinal",
    "PMD.LawOfDemeter"
})
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestStorage
{
    @ParameterizedTest(name = "{index} => size=''{0}''")
    @ValueSource(strings = {"11", "12", "1", "10", "7", "5", "100"})
    public void testParseValid(String intAsString)
    {
        assertThat(
            Storage.getInventorySize("file.txt", intAsString),
            equalTo(Integer.parseInt(intAsString))
        );
    }

    @Test
    public void testParseInvalidTooFewArgs()
    {
        assertThat(Storage.getInventorySize("file.txt"), equalTo(DEFAULT_SIZE));
    }

    @Test
    public void testParseInvalidNotAnInt()
    {
        assertThat(
            Storage.getInventorySize("file.txt", "The Number Ten"),
            equalTo(DEFAULT_SIZE)
        );
    }

    @ParameterizedTest(name = "{index} => size=''{0}''")
    @ValueSource(strings = {"0", "-1", "-100", "-10", "-5", "-32"})
    public void testParseInvalidZeroOrLess(String intAsString)
    {
        assertThat(Storage.getInventorySize("file.txt", intAsString), equalTo(DEFAULT_SIZE));
    }
}

