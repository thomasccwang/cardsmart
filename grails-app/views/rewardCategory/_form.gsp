<%@ page import="com.cardsmart.RewardCategory" %>



<div class="fieldcontain ${hasErrors(bean: rewardCategoryInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="rewardCategory.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${rewardCategoryInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rewardCategoryInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="rewardCategory.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${rewardCategoryInstance?.description}"/>
</div>

