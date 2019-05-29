Vue.filter('questionType', function (value) {
  if (!value) return ''
  let result='';
  if(value==1)result='单选题';
  else if(value===2)result='多选题';
  else if(value===4)result='判断题';
  else result='分录';
  return result;
})
