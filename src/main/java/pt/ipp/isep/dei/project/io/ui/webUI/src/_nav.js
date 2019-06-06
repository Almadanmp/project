export default {
  items: [
    {
      name: 'Dashboard',
      url: '/dashboard',
      icon: 'icon-speedometer',
      badge: {
        variant: 'info',
        text: 'NEW',
      },
    },
    {
      title: true,
      name: 'SMART',
      wrapper: {            // optional wrapper object
        element: '',        // required valid HTML5 element tag
        attributes: {}        // optional valid JS object with JS API naming ex: { className: "my-class", style: { fontFamily: "Verdana" }, id: "my-id"}
      },
      class: ''             // optional class names space delimited list for title item ex: "text-center"
    },
    {
      name: 'Area',
      url: '/maintenance',
      icon: 'icon-globe',
    },
    {
      name: 'House',
      url: '/house',
      icon: 'icon-home',
      children: [
        {
          name: 'House Monitoring',
          url: '/house/monitoring',
          icon: 'cui-sun icons',
          },
        {
          name: 'House Configuration',
          url: '/house/configuration',
          icon: 'fa fa-wrench fa-lg',
        },
      ],
    },
    {
      name: 'Room',
      url: '/theme/room',
      icon: 'fa fa-bed fa-lg',
      children: [
        {
          name: 'Room Monitoring',
          url: '/room/monitoring',
          icon: 'fa fa-thermometer fa-lg',
        },
        {
          name: 'Room Configuration',
          url: '/room/configuration',
          icon: 'fa fa-wrench fa-lg',
        },
      ],
    },
    {
      name: 'Energy',
      url: '/maintenance',
      icon: 'icon-energy\n',
    },
    {
      name: 'Sensor',
      url: '/maintenance',
      icon: 'icon-speedometer\n',
    },

    {
      divider: true,
    },
    {
      title: true,
      name: 'Extras',
    },
    {
      name: 'Pages',
      url: '/pages',
      icon: 'icon-star',
      children: [
        {
          name: 'Login',
          url: '/login',
          icon: 'icon-star',
        },
        {
          name: 'Register',
          url: '/register',
          icon: 'icon-star',
        },
        {
          name: 'Error 404',
          url: '/404',
          icon: 'icon-star',
        },
        {
          name: 'Error 500',
          url: '/500',
          icon: 'icon-star',
        },
      ],
    },
    {
      name: 'Disabled',
      url: '/dashboard',
      icon: 'icon-ban',
      attributes: { disabled: true },
    },
  ],
};
