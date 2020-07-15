package com.kira.emercmdplat.annotation;

/**
 * @Author: kira
 * @Date: 2020/7/7 11:09
 * @Description:验证手机号固话，空和正确的手机号都能验证通过<br/>
 * 正确的手机号由11位数字组成，第一位为1,第二位为 3、4、5、7、8
 */
import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

@ConstraintComposition(CompositionType.OR)
@Pattern(regexp = "^(((\\+\\d{2}-)?0\\d{2,3}-\\d{7,8})|((\\+\\d{2}-)?(\\d{2,3}-)?([1][3,4,5,7,8][0-9]\\d{8})))$")
@Null
@Length(min = 0, max = 0)
@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface ContactNum {

    String message() default "号格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
