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

<div class="fieldcontain ${hasErrors(bean: rewardCategoryInstance, field: 'parent', 'error')} ">
	<label for="parent">
		<g:message code="rewardCategory.parent.label" default="Parent" />
		
	</label>
	<g:select id="parent" name="parent.id" from="${com.cardsmart.RewardCategory.list()}" optionKey="id" value="${rewardCategoryInstance?.parent?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rewardCategoryInstance, field: 'children', 'error')} ">
	<label for="children">
		<g:message code="rewardCategory.children.label" default="Children" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${rewardCategoryInstance?.children?}" var="c">
    <li><g:link controller="rewardCategory" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="rewardCategory" action="create" params="['rewardCategory.id': rewardCategoryInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'rewardCategory.label', default: 'RewardCategory')])}</g:link>
</li>
</ul>

</div>

