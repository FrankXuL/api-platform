export default [
  {
    path: '/',
    name: '主页',
    icon: 'smile',
    component: './Index/welcome'
  },
  {path: '/interface_info/:id', name: '查看接口', icon: 'smile', component: './InterfaceInfo', hideInMenu: true},
  {path: '/user', layout: false, routes: [{path: '/user/login', component: './User/Login'}]},
  {
    path: '/admin',
    name: '管理页',
    icon: 'crown',
    access: 'canAdmin',
    routes: [
      {
        icon: 'table', path: '/admin/interface_info', name: '接口管理', component: './Admin/InterfaceInfo'
      },
      {path: '/admin', redirect: '/admin/sub-page'},
      {path: '/admin/sub-page', component: './Admin'},
    ],
  },
  {path: '*', layout: false, component: './404'},
];
